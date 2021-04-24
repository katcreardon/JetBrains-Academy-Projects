package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] arr = getTextData(args[0]);
        getUserSelection(arr);
    }

    static void getUserSelection(int[] arr) {
        System.out.print("Enter the score you want to calcluate (ARI, FK, SMOG, CL, all): ");
        Scanner in = new Scanner(System.in);
        String selection = in.nextLine();
        if (selection.equals("ARI")) {
            System.out.println();
            automatedReadabilityIndex(arr);
        } else if (selection.equals("FK")) {
            System.out.println();
            fleschKincaid(arr);
        } else if (selection.equals("SMOG")) {
            System.out.println();
            smog(arr);
        } else if (selection.equals("CL")) {
            System.out.println();
            colemanLiau(arr);
        } else if (selection.equals("all")) {
            System.out.println();
            int a = automatedReadabilityIndex(arr);
            int b = fleschKincaid(arr);
            int c = smog(arr);
            int d = colemanLiau(arr);
            double avg = (double) (a + b + c + d) / 4;
            System.out.printf("%nThis text should be understood in average by %.2f-year-olds.", avg);
        }
    }

    static void countSymbols() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (line.length() <= 100) {
            System.out.println("EASY");
        } else {
            System.out.println("HARD");
        }
    }

    static int countSyllables(String word) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        int totalVowels = 0;
        for (int i = 0; i < word.length(); i++) {
            // If the character is a vowel
            if (vowels.contains(word.charAt(i))) {
                // If the character has a following consonant
                if (i != word.length() - 1 && !vowels.contains(word.charAt(i + 1))) {
                    totalVowels += 1;
                }
                // If the vowel is not an ending 'e' (also covers 1-letter words "a" and "I")
                else if (i == word.length() - 1 && word.charAt(i) != 'e') {
                    totalVowels += 1;
                }
            }
        }
        // Words with 0 vowels are 1 syllable
        return Math.max(totalVowels, 1);
    }

    static int[] calculateParameters(String line) {
//        Scanner in = new Scanner(System.in);
//        String line = in.nextLine();
        String[] sentences = line.split("(?<=[!.?])\\s+");
        int totalWords = 0;
        int totalSentences = sentences.length;
        int totalChars = 0;
        int totalSyllables = 0;
        int totalPolysyllables = 0;
        for (int i = 0; i < totalSentences; i++) {
            String[] words = sentences[i].trim().split("\\s+");
            totalWords += words.length;
            for (String word : words) {
                totalChars += word.length();
                int currSyllables = countSyllables(word);
                totalSyllables += currSyllables;
                if (currSyllables > 2) {
                    totalPolysyllables += 1;
                }
            }
        }

        return new int[] {totalWords, totalSentences, totalChars, totalSyllables, totalPolysyllables};
//        double avg = sum / numSentences;
//        if (avg <= 10) {
//            System.out.println("EASY");
//        } else {
//            System.out.println("HARD");
//        }
    }

    static int[] getTextData(String inputFile) {
        try {
            String fileAsString = readFileAsString(inputFile);
            int[] arr = calculateParameters(fileAsString);
            System.out.printf("The text is:%n%s%n%nWords: %d%nSentences: %d%nCharacters: %d%nSyllables: %d%n" +
                    "Polysyllables: %d%n", fileAsString, arr[0], arr[1], arr[2], arr[3], arr[4]);
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[] {};
    }

    static int automatedReadabilityIndex(int[] arr) {
        double score = 4.71 * (double) arr[2] / arr[0] + 0.5 * (double) arr[0] / arr[1] - 21.43;
        int roundedScore = (int) Math.round(score);
        int ageRange = 0;
        switch (roundedScore) {
            case 1:
                ageRange = 6;
                break;
            case 2:
                ageRange = 7;
                break;
            case 3:
                ageRange = 9;
                break;
            case 4:
                ageRange = 10;
                break;
            case 5:
                ageRange = 11;
                break;
            case 6:
                ageRange = 12;
                break;
            case 7:
                ageRange = 13;
                break;
            case 8:
                ageRange = 14;
                break;
            case 9:
                ageRange = 15;
                break;
            case 10:
                ageRange = 16;
                break;
            case 11:
                ageRange = 17;
                break;
            case 12:
                ageRange = 18;
                break;
            case 13:
                ageRange = 24;
                break;
        }
        if (roundedScore == 14) {
            System.out.printf("Automated Readability Index: %.2f (about 24+-year-olds).%n", score);
            return 24;
        } else {
            System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).%n", score, ageRange);
            return ageRange;
        }
    }

    static int fleschKincaid(int[] arr) {
        double score = 0.39 * (double) arr[0] / arr[1] + 11.8 * (double) arr[3] / arr[0] - 15.59;
        int roundedScore = (int) Math.round(score);
        int ageRange = 0;
        switch (roundedScore) {
            case 1:
                ageRange = 6;
                break;
            case 2:
                ageRange = 7;
                break;
            case 3:
                ageRange = 9;
                break;
            case 4:
                ageRange = 10;
                break;
            case 5:
                ageRange = 11;
                break;
            case 6:
                ageRange = 12;
                break;
            case 7:
                ageRange = 13;
                break;
            case 8:
                ageRange = 14;
                break;
            case 9:
                ageRange = 15;
                break;
            case 10:
                ageRange = 16;
                break;
            case 11:
                ageRange = 17;
                break;
            case 12:
                ageRange = 18;
                break;
            case 13:
                ageRange = 24;
                break;
        }
        if (roundedScore == 14) {
            System.out.printf("Flesch-Kincaid readability tests: %.2f (about 24+-year-olds).%n", score);
            return 24;
        } else {
            System.out.printf("Flesch-Kincaid readability tests: %.2f (about %s-year-olds).%n", score, ageRange);
            return ageRange;
        }
    }

    static int smog(int[] arr) {
        double score = 1.043 * Math.sqrt(arr[4] * 30 / arr[1]) + 3.1291;
        int roundedScore = (int) Math.round(score);
        int ageRange = 0;
        switch (roundedScore) {
            case 1:
                ageRange = 6;
                break;
            case 2:
                ageRange = 7;
                break;
            case 3:
                ageRange = 9;
                break;
            case 4:
                ageRange = 10;
                break;
            case 5:
                ageRange = 11;
                break;
            case 6:
                ageRange = 12;
                break;
            case 7:
                ageRange = 13;
                break;
            case 8:
                ageRange = 14;
                break;
            case 9:
                ageRange = 15;
                break;
            case 10:
                ageRange = 16;
                break;
            case 11:
                ageRange = 17;
                break;
            case 12:
                ageRange = 18;
                break;
            case 13:
                ageRange = 24;
                break;
        }
        if (roundedScore == 14) {
            System.out.printf("Simple Measure of Gobbledygook: %.2f (about 24+-year-olds).%n", score);
            return 24;
        } else {
            System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).%n", score, ageRange);
            return ageRange;
        }
    }

    static int colemanLiau(int[] arr) {
        double l = (double) arr[2] / arr[0] * 100;
        double s = (double) arr[1] / arr[0] * 100;
        double score = 0.0588 * l - 0.296 * s - 15.8;
        int roundedScore = (int) Math.round(score);
        int ageRange = 0;
        switch (roundedScore) {
            case 1:
                ageRange = 6;
                break;
            case 2:
                ageRange = 7;
                break;
            case 3:
                ageRange = 9;
                break;
            case 4:
                ageRange = 10;
                break;
            case 5:
                ageRange = 11;
                break;
            case 6:
                ageRange = 12;
                break;
            case 7:
                ageRange = 13;
                break;
            case 8:
                ageRange = 14;
                break;
            case 9:
                ageRange = 15;
                break;
            case 10:
                ageRange = 16;
                break;
            case 11:
                ageRange = 17;
                break;
            case 12:
                ageRange = 18;
                break;
            case 13:
                ageRange = 24;
                break;
        }
        if (roundedScore == 14) {
            System.out.printf("Coleman-Liau index: %.2f (about 24+-year-olds).%n", score);
            return 24;
        } else {
            System.out.printf("Coleman-Liau index: %.2f (about %d-year-olds).%n", score, ageRange);
            return ageRange;
        }
    }

    static String readFileAsString (String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
