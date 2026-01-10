package com.example.demo.sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Walklesson {
    public static void main(String[] args) {
        File file = new File("C:/Users/takah/Desktop/OpenCSV/Sample.txt");
        Path path = Paths.get("C:/pleiades-2020-12-java-win-64bit-jre_20201222/pleiades/workspace");
        Path pathJavaFile = Paths.get("C:/pleiades-2020-12-java-win-64bit-jre_20201222/pleiades/workspace/Employee/src/web/employee/TopServlet.java");

        try(BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            List<String> list = Files.readAllLines(pathJavaFile);

            for (String string : list) {
                out.write(string);
//                System.out.println(string);
            }
            Files.walk(path)
            .filter(s -> s.toString().endsWith(".java")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
