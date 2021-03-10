package com.employeepayroll;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class WatchService {
    private java.nio.file.WatchService watchService;
    private HashMap<WatchKey, Path> directoryWatchers;

    // create a watcher and registers given directory
    public WatchService(Path dir) throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.directoryWatchers = new HashMap<WatchKey, Path>();
        scanAndRegisterDirectories(dir);
    }

    private void scanAndRegisterDirectories(Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirWatchers(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void registerDirWatchers(Path dir) throws IOException {
        WatchKey key = dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        directoryWatchers.put(key, dir);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void processEvents() {
        while (true) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (InterruptedException e) {
                return;
            }
            Path dir = directoryWatchers.get(key);
            if (dir == null) continue;
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);
                System.out.format("%s : %s\n", event.kind().name(), child);

                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child)) scanAndRegisterDirectories(child);
                    } catch (IOException e) {
                    }
                } else if (kind.equals(ENTRY_DELETE)) {
                    if (Files.isDirectory(child)) directoryWatchers.remove(child);
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                directoryWatchers.remove(key);
                if (directoryWatchers.isEmpty()) break;
            }
        }
    }
}
