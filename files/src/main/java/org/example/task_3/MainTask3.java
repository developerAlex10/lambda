package org.example.task_3;

import org.example.task_2.GameProgress;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainTask3 {
    public static void main(String[] args) {
        String zipPath = "D:/Games/savegames/saves.zip";
        String unpackDir = "D:/Games/savegames/";

        openZip(zipPath, unpackDir);

        File saveDir = new File(unpackDir);
        File[] saveFiles = saveDir.listFiles((dir, name) -> name.endsWith(".dat"));

        if (saveFiles != null) {
            GameProgress progress = openProgress(saveFiles[1].getPath());
            System.out.println("Состояние сохраненной игры: " + progress);
        } else {
            System.out.println("Файлы сохранений не найдены");
        }
    }

    public static void openZip(String zipPath, String unpackDir) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                File file = new File(unpackDir, entry.getName());
                FileOutputStream fout = new FileOutputStream(file);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при распаковке архива: " + e.getMessage());
        }
    }

    public static GameProgress openProgress(String filePath) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
            System.out.println("Состояние игры успешно загружено из " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке состояния игры: " + e.getMessage());
        }
        return gameProgress;
    }
}
