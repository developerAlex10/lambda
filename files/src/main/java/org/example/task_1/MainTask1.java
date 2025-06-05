package org.example.task_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainTask1 {
    public static void main(String[] args) {
        String path = "D:/Games";
        StringBuilder log = new StringBuilder();

        File src = createDir(path, "/src", log);
        File res = createDir(path, "/res", log);
        File saveGames = createDir(path, "/savegames", log);
        File temp = createDir(path, "/temp", log);

        File main = createDir(src.getPath(), "/main", log);
        File test = createDir(src.getPath(), "/test", log);

        createFile(main.getPath(), "/Main.java", log);
        createFile(main.getPath(), "/Utils.java", log);

        File drawables = createDir(res.getPath(), "/drawables", log);
        File vectors = createDir(res.getPath(), "/vectors", log);
        File icons = createDir(res.getPath(), "/icons", log);

        File tempFile = createFile(temp.getPath(), "/temp.txt", log);

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан в temp.txt");
        } catch (IOException e) {
            System.out.println("Ошибка при записи лога: " + e.getMessage());
        }
    }

    public static File createDir(String path, String nameDir, StringBuilder log) {
        File dir = new File(path + nameDir);
        if (dir.mkdir()) {
            log.append("Каталог ").append(nameDir.substring(1)).append(" создан успешно\n");
        }
        return dir;
    }

    private static File createFile(String path, String nameFile, StringBuilder log) {
        File file = new File(path + nameFile);
        try {
            if (file.createNewFile()) {
                log.append("Файл ").append(nameFile.substring(1)).append(" создан успешно\n");
            }
        } catch (IOException e) {
            log.append("Ошибка: Каталог  ").append(nameFile.substring(1)).append(" не создан\n").append(e.getMessage());
        }
        return file;
    }
}