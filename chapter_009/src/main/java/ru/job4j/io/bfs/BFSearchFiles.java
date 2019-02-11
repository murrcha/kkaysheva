package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * BFSearchFiles - breadth-first search files
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class BFSearchFiles {

    public List<File> search(String parent, List<String> exts) {
        File parentFile = new File(parent);
        List<File> result = new ArrayList<>();
        Deque<File> directories = new LinkedList<>();
        if (parentFile.isDirectory()) {
            directories.add(parentFile);
            while (!directories.isEmpty()) {
                File directory = directories.removeFirst();
                parseDirectory(directory, directories, result, exts);
            }
        } else {
            throw new IllegalArgumentException("Argument parent is not directory");
        }
        return result;
    }

    private void parseDirectory(File directory,
                                Deque<File> directories,
                                List<File> result,
                                List<String> exts) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    directories.addLast(file);
                } else {
                    if (isFileInExts(file, exts)) {
                        result.add(file);
                    }
                }
            }
        }
    }

    private boolean isFileInExts(File file, List<String> exts) {
        boolean result = false;
        for (String ext : exts) {
            if (file.getName().endsWith(ext)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
