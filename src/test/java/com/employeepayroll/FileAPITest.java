package com.employeepayroll;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class FileAPITest {
    private String Home = System.getProperty("user.home");
    private String testPath = "TestDirectory";

    @Test
    void givenDirectoryShouldCheckIfItAlreadyExsists() throws IOException {
        Path path = Paths.get(Home);
        Assertions.assertTrue(Files.exists(path));

        // check if directory exists and delete
        Path newPath = Paths.get(testPath);
        if (Files.exists(newPath)) FilesUtility.deleteAllFiles(newPath.toFile());
        Assertions.assertTrue(Files.notExists(newPath));

        // create a new Directory
        Files.createDirectory(newPath);
        Assertions.assertTrue(Files.exists(newPath));

        // IntStream is just used a fixed loop
        IntStream.range(1, 10).forEach(fileNumber -> {
            String filePath = testPath + "/" + "TempFile" + fileNumber;
            Path tempPath = Paths.get(filePath);
            try {
                Files.createFile(tempPath);
            } catch (IOException e) {
            }
            Assertions.assertTrue(Files.exists(tempPath));
        });

        //Iterating to list of files in directory and converting path object to file object for file operations
        Path p = Paths.get(testPath);
        File file = p.toFile();
        File[] listOfFile = file.listFiles();
        for (File f : listOfFile) {
            System.out.println(f);
        }

        // listing files using streams
        Files.list(Path.of(testPath)).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(Path.of(testPath)).forEach(System.out::println);
        Files.newDirectoryStream(Path.of(testPath), pathOne -> pathOne.toFile().isFile() && pathOne.toString().startsWith("Temp")).forEach(System.out::println);
    }
}
