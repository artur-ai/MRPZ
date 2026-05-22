package lab4;

import java.io.*;
import java.util.Scanner;

public class Lab4 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть шлях до вхідного файлу (Java-програма): ");
        String inputPath = scanner.nextLine().trim();

        System.out.print("Введіть шлях до результуючого файлу: ");
        String outputPath = scanner.nextLine().trim();

        scanner.close();

        File inputFile = new File(inputPath);

        if (!inputFile.exists()) {
            System.out.println("[ПОМИЛКА] Файл не знайдено: " + inputPath);
            return;
        }

        System.out.println("\n[ІНФО] Відкриваємо файл: " + inputFile.getAbsolutePath());
        System.out.println("[ІНФО] Розмір файлу: " + inputFile.length() + " байт");

        StringBuilder result = new StringBuilder();
        int totalWords = 0;
        int changedWords = 0;
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            System.out.println("[ІНФО] Читання файлу...\n");

            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                String processedLine = processLine(line);

                String[] words = line.split("\\s+");
                for (String word : words) {
                    String clean = word.replaceAll("[^a-zA-Zа-яА-ЯіІїЇєЄ]", "");
                    if (!clean.isEmpty()) {
                        totalWords++;
                        if (clean.length() > 2) {
                            changedWords++;
                        }
                    }
                }

                result.append(processedLine).append(System.lineSeparator());
            }

            System.out.println("[ІНФО] Прочитано рядків: " + lineCount);
            System.out.println("[ІНФО] Загальна кількість слів: " + totalWords);
            System.out.println("[ІНФО] Слів із більше ніж 2 символами (змінено): " + changedWords);

        } catch (IOException e) {
            System.out.println("[ПОМИЛКА] Помилка читання файлу: " + e.getMessage());
            return;
        }

        System.out.println("\n[ІНФО] Запис результату у файл: " + outputPath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write(result.toString());
            System.out.println("[ІНФО] Файл успішно збережено: " + new File(outputPath).getAbsolutePath());
        } catch (IOException e) {
            System.out.println("[ПОМИЛКА] Помилка запису файлу: " + e.getMessage());
            return;
        }

        System.out.println("\n=== РЕЗУЛЬТАТ ===");
        System.out.println("Оброблено рядків:              " + lineCount);
        System.out.println("Всього слів:                   " + totalWords);
        System.out.println("Слів змінено (>2 символи):     " + changedWords);
        System.out.println("Результат збережено у:         " + outputPath);
    }

    private static String processLine(String line) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while (i < line.length()) {
            if (Character.isLetterOrDigit(line.charAt(i)) || line.charAt(i) == '_') {
                int start = i;
                while (i < line.length() && (Character.isLetterOrDigit(line.charAt(i)) || line.charAt(i) == '_')) {
                    i++;
                }
                String word = line.substring(start, i);

                if (word.length() > 2) {
                    sb.append(word.toUpperCase());
                } else {
                    sb.append(word);
                }
            } else {
                sb.append(line.charAt(i));
                i++;
            }
        }

        return sb.toString();
    }
}
