package com.employeepayroll;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;

public class WatchServiceTest {
    @Test
    public void givenADirectoryAllEventsMustBeListedAndLogged() throws IOException {
        Path dir = Paths.get("TestDirectory");
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        new WatchService(dir).processEvents();
    }
}
