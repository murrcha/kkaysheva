package ru.job4j.parallelsearch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ParallelSearch - search text in file system
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class ParallelSearch {

    private final String root;
    private final String text;
    private final List<String> extensions;
    private volatile boolean finish = false;

    @GuardedBy("this")
    private final BlockingQueue<String> files = new LinkedBlockingQueue<>();

    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();

    /**
     * init root, text, extensions
     * @param root directory
     * @param text pattern
     * @param extensions files
     */
    public ParallelSearch(String root, String text, List<String> extensions) {
        this.root = root;
        this.text = text;
        this.extensions = extensions;
    }

    /**
     * Method getPattern create pattern from list extensions
     * @param extensions list
     * @return pattern
     */
    private String getPattern(List<String> extensions) {
        StringBuilder pattern = new StringBuilder();
        pattern.append("*.{");
        for (String extension : extensions) {
            pattern.append(extension);
            pattern.append(",");
        }
        pattern.append("}");
        pattern.deleteCharAt(pattern.lastIndexOf(","));
        return pattern.toString();
    }

    /**
     * Method filesIsEmpty
     * @return flag
     */
    private boolean filesIsEmpty() {
        return files.isEmpty();
    }

    /**
     * Method getFiles
     * @return files
     */
    private BlockingQueue<String> getFiles() {
        return this.files;
    }

    /**
     * Method init describe and start search, read threads
     */
    public void init() {
        Thread search = new Thread(() -> {
            FinderFiles finder = new FinderFiles(getPattern(extensions), getFiles());
            Path startPath = Paths.get(root);
            try {
                Files.walkFileTree(startPath, finder);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            finish = true;
        });
        Thread read = new Thread(() -> {
            while (!filesIsEmpty() || !finish) {
                try {
                    Path name = Paths.get(getFiles().take());
                    if (name != null) {
                        String content = new String(Files.readAllBytes(name));
                        if (content.contains(text)) {
                            System.out.println(String.format("Add file [%s] to paths list (contains [%s])", name, text));
                            paths.add(name.toString());
                        }
                    }
                } catch (IOException | InterruptedException ioe) {
                    ioe.printStackTrace();
                    return;
                }
            }
        });
        search.start();
        read.start();
        try {
            search.join();
            read.join(5000);
            if (read.isAlive()) {
                read.interrupt();
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Method result return result after search
     * @return result list
     */
    public synchronized List<String> result() {
        return this.paths;
    }
}
