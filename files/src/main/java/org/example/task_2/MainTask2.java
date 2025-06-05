package org.example.task_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MainTask2 {

    public static void main(String[] args) {
        GameProgress progress_1 = new GameProgress(100, 15, 5, 0.0);
        GameProgress progress_2 = new GameProgress(50, 8, 10, 497.5);
        GameProgress progress_3 = new GameProgress(1, 3, 50, 1000.9);

        String saveDir = "D:/Games/savegames";
        List<String> savedFiles = new ArrayList<>();

        savedFiles.add(saveGame(saveDir + "/save1.dat", progress_1));
        savedFiles.add(saveGame(saveDir + "/save2.dat", progress_2));
        savedFiles.add(saveGame(saveDir + "/save3.dat", progress_3));

        zipFiles(saveDir + "/saves.zip", savedFiles);

        deleteFiles(savedFiles);
    }

    private static String saveGame(String filePath, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(filePath); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
            return filePath;
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
            return null;
        }
    }

    private static void zipFiles(String zipPath, List<String> filesToZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String filePath : filesToZip) {
                if (filePath == null) continue;
                File file = new File(filePath);
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException e) {
                    System.out.println("Ошибка при добавлении файла в архив: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
        }
    }

    private static void deleteFiles(List<String> filesToDelete) {
        for (String filePath : filesToDelete) {
            if (filePath == null) continue;

            File file = new File(filePath);
            if (file.delete()) {
                System.out.println("Файл " + filePath + " удален");
            }
        }
    }
}
