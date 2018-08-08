package ru.job4j.parallelsearch;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.BlockingQueue;

/**
 * FinderFiles
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class FinderFiles extends SimpleFileVisitor<Path> {
    /**
     * Matcher
     */
    private final PathMatcher matcher;

    /**
     * Queue files of files matches
     */
    private final BlockingQueue<String> files;

    /**
     * init FinderFiles
     * @param pattern
     * @param files
     */
    public FinderFiles(String pattern, BlockingQueue<String> files) {
        matcher = FileSystems.getDefault().getPathMatcher(String.format("glob:%s", pattern));
        this.files = files;
    }

    /**
     * Method findFiles find files by pattern
     * @param file start directory for finding
     */
    private void findFiles(Path file) throws InterruptedException {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            System.out.println(String.format("Put file [%s] to files queue", file));
            files.put(file.toString());
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        try {
            findFiles(file);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }
}
