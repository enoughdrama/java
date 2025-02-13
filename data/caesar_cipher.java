// Документация: https://enoughdrama.gitbook.io/documentation

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Caesar {

    private static String _ALPH_UPPER = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static String _ALPH_LOWER = "абвгдежзийклмнопрстуфхцчшщъыьэюя";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------ Режим работы ------");
        System.out.println("1 - Шифрование");
        System.out.println("2 - Расшифрование по ключу");
        System.out.println("3 - Brute force");
        System.out.println("4 - Статистический анализ");

        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод.");
            return;
        }

        switch (choice) {
            case 1:
                doEncrypt(scanner);
                break;
            case 2:
                doDecrypt(scanner);
                break;
            case 3:
                doBruteForce(scanner);
                break;
            case 4:
                doStat(scanner);
                break;
            default:
                System.out.println("Некорректный выбор.");
                break;
        }
        scanner.close();
    }

    private static void doEncrypt(Scanner scanner) {
        System.out.println("-> путь к исходному файлу:");
        String source = scanner.nextLine();
        System.out.println("-> путь к выходному (зашифрованному) файлу:");
        String output = scanner.nextLine();
        System.out.println("-> ключ (целое число):");

        int cipherKey;
        try {
            cipherKey = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ключ.");
            return;
        }

        Path sourcePath = Path.of(source);
        if (!Files.exists(sourcePath)) {
            System.out.println("Файл не найден.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(sourcePath);
                BufferedWriter writer = Files.newBufferedWriter(Path.of(output))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String encryptedLine = encryptLine(line, cipherKey);
                writer.write(encryptedLine);
                writer.newLine();
            }
            System.out.println("Шифрование завершено.");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void doDecrypt(Scanner scanner) {
        System.out.println("-> путь к зашифрованному файлу:");
        String source = scanner.nextLine();
        System.out.println("-> путь к выходному (расшифрованному) файлу:");
        String output = scanner.nextLine();
        System.out.println("-> ключ (целое число):");
        int cipherKey;
        try {
            cipherKey = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ключ.");
            return;
        }

        Path sourcePath = Path.of(source);
        if (!Files.exists(sourcePath)) {
            System.out.println("Файл не найден.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(sourcePath);
                BufferedWriter writer = Files.newBufferedWriter(Path.of(output))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String decryptedLine = decryptLine(line, cipherKey);
                writer.write(decryptedLine);
                writer.newLine();
            }
            System.out.println("Расшифровка завершена.");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void doBruteForce(Scanner scanner) {
        System.out.print("-> Введите путь к зашифрованному файлу: ");
        String source = scanner.nextLine().trim();
        System.out.print("-> Введите путь для сохранения расшифрованного файла: ");
        String output = scanner.nextLine().trim();
    
        Path sourcePath = Path.of(source);
        if (!Files.exists(sourcePath)) {
            System.out.println("Файл не найден: " + source);
            return;
        }
    
        String cipherText;
        try {
            cipherText = Files.readString(sourcePath);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }
    
        int alphSize = _ALPH_UPPER.length();
        boolean success = false;
        String finalDecryptedText = null;
        int finalKey = -1;
    
        for (int k = 0; k < alphSize; k++) {
            String candidate = decryptLine(cipherText, k);
            System.out.println("\nПроверяем ключ: " + k);

            String preview = candidate.length() > 50 ? candidate.substring(0, 50) + "..." : candidate;
            System.out.println("Превью расшифрованного текста:\n" + preview);
            System.out.print("Верен ли данный вариант расшифровки (y/n): ");
            
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("y") || response.equals("yes")) {
                success = true;
                finalDecryptedText = candidate;
                finalKey = k;
                break;
            }
        }
    
        if (success) {
            System.out.println("\nУспешная расшифровка с ключом" + finalKey);
            try {
                Files.writeString(Path.of(output), finalDecryptedText);
                System.out.println("Расшифрованный файл успешно записан");
            } catch (IOException e) {
                System.out.println("Ошибка записи файла: " + e.getMessage());
            }
        } else {
            System.out.println("\nНе удалось подобрать корректный ключ.");
        }
    }

    private static void doStat(Scanner scanner) {
        System.out.println("-> путь к зашифрованному файлу:");
        String source = scanner.nextLine();
        System.out.println("-> путь к файлу-образцу (пример незашифрованного текста):");
        String sample = scanner.nextLine();
        System.out.println("-> путь к выходному (расшифрованному) файлу:");
        String output = scanner.nextLine();

        Path sourcePath = Path.of(source);
        Path samplePath = Path.of(sample);

        if (!Files.exists(sourcePath) || !Files.exists(samplePath)) {
            System.out.println("Один из файлов не найден.");
            return;
        }

        try {
            String encryptedText = Files.readString(sourcePath);
            String sampleText = Files.readString(samplePath);

            double[] freqU = calcFrequency(sampleText, _ALPH_UPPER);
            double[] freqL = calcFrequency(sampleText, _ALPH_LOWER);

            int _ALPH_SIZE = _ALPH_UPPER.length();
            double minDiff = Double.MAX_VALUE;
            int predictedCipherKey = -1;
            String guessedOrigin = null;

            for (int k = 0; k < _ALPH_SIZE; k++) {
                String candidate = decryptLine(encryptedText, k);
                double[] candidateU = calcFrequency(candidate, _ALPH_UPPER);
                double[] candidateL = calcFrequency(candidate, _ALPH_LOWER);

                double diff = CompareFrequency(freqU, candidateU) + CompareFrequency(freqL, candidateL);
                if (diff < minDiff) {
                    minDiff = diff;
                    predictedCipherKey = k;
                    guessedOrigin = candidate;
                }
            }

            System.out.println("Лучший ключ: " + predictedCipherKey);
            Files.writeString(Path.of(output), guessedOrigin);

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static String encryptLine(String text, int cipherKey) {
        StringBuilder sb = new StringBuilder();
        int _ALPH_SIZE = _ALPH_UPPER.length();

        cipherKey = cipherKey % _ALPH_SIZE;
        if (cipherKey < 0) {
            cipherKey = cipherKey + _ALPH_SIZE;
        }

        for (char ch : text.toCharArray()) {
            if (_ALPH_UPPER.indexOf(ch) != -1) {
                int pos = _ALPH_UPPER.indexOf(ch);
                int target = (pos + cipherKey) % _ALPH_SIZE;
                sb.append(_ALPH_UPPER.charAt(target));
            } else if (_ALPH_LOWER.indexOf(ch) != -1) {
                int pos = _ALPH_LOWER.indexOf(ch);
                int target = (pos + cipherKey) % _ALPH_SIZE;
                sb.append(_ALPH_LOWER.charAt(target));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private static String decryptLine(String text, int cipherKey) {
        return encryptLine(text, -cipherKey);
    }

    private static double[] calcFrequency(String text, String alphabet) {
        double[] freq = new double[alphabet.length()];
        int totalCount = 0;

        for (char ch : text.toCharArray()) {
            int idx = alphabet.indexOf(ch);
            if (idx != -1) {
                freq[idx]++;
                totalCount++;
            }
        }

        for (int i = 0; i < freq.length; i++) {
            if (totalCount != 0) {
                freq[i] /= totalCount;
            }
        }
        return freq;
    }

    private static double CompareFrequency(double[] freq1, double[] freq2) {
        double sum = 0.0;
        for (int i = 0; i < freq1.length; i++) {
            double diff = freq1[i] - freq2[i];
            sum += diff * diff;
        }
        return sum;
    }
}
