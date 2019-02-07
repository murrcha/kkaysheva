package ru.job4j.io.archive;

import java.io.*;
import java.net.URI;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Archiver
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class Archiver {

    private static final int BUFFER_SIZE = 1024;

    public void zipArchive(File directory, List<String> exts, File outZipFile) throws IOException {
        URI base = directory.toURI();
        Deque<File> queue = new LinkedList<>();
        queue.push(directory);
        try (ZipOutputStream zipOut =
                     new ZipOutputStream(new FileOutputStream(outZipFile))) {
            while (!queue.isEmpty()) {
                directory = queue.pop();
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        String name = base.relativize(file.toURI()).getPath();
                        if (file.isDirectory()) {
                            queue.push(file);
                            name = name.endsWith("/") ? name : name + "/";
                            zipOut.putNextEntry(new ZipEntry(name));
                        } else if (isFileInExts(file, exts)) {
                            zipOut.putNextEntry(new ZipEntry(name));
                            copy(file, zipOut);
                            zipOut.closeEntry();
                        }
                    }
                }
            }
        }
    }

    private void copy(File file, OutputStream out) throws IOException {
        try (InputStream in = new FileInputStream(file)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int readCount;
            while ((readCount = in.read(buffer)) > 0) {
                out.write(buffer, 0, readCount);
            }
        }
    }

    @SuppressWarnings("Duplicates")
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
