package bullscows;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        playGame();
    }

    static void printPredefinedGame() {
        System.out.println("The secret code is prepared: ****.");
        System.out.println();
        System.out.println("Turn 1. Answer:");
        System.out.println("1234");
        System.out.println("Grade: None.");
        System.out.println();
        System.out.println("Turn 2. Answer:");
        System.out.println("9876");
        System.out.println("Grade: 4 bulls.");
        System.out.println("Congrats! The secret code is 9876.");
    }

    static void playGame() {
        String[] params = generateSecretCode();
        gradeAnswer(params);
    }

    static String[] generateSecretCode() {
        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        int n = 0;
        int m = 0;
        System.out.println("Input the length of the secret code:");
        Scanner in = new Scanner(System.in);

        if (in.hasNextInt()) {
            n = in.nextInt();
            in.nextLine();
        } else {
            String err = in.nextLine();
            System.out.printf("Error: \"%s\" isn't a valid number.", err);
            System.exit(0);
        }

        if (n < 1) {
            System.out.println("Error: the code must be longer than 0.");
            System.exit(0);
        }

        if (n > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough" +
                    " unique symbols.", n);
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code:");
        if (in.hasNextInt()) {
            m = in.nextInt();
            in.nextLine();
        } else {
            String err = in.nextLine();
            System.out.printf("Error: \"%s\" isn't a valid number.", err);
            System.exit(0);
        }

        if (m > 36) {
            System.out.printf("Error: can't generate a secret number using %d symbols because there aren't enough.", m);
            System.exit(0);
        }

        if (m < n) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.",
                    n, m);
            System.exit(0);
        }

        Random random = new Random();
        String code = "";
        String stars = "";
        StringBuilder sb = new StringBuilder(code);

        while (sb.length() < n) {
            int i = random.nextInt(m);
            char digit = symbols.charAt(i);
            // If digit is not already present in code
            if (sb.indexOf(Character.toString(digit)) == -1) {
                sb.append(digit);
            }
        }

        for (int i = 0; i < n; i++) {
            stars += "*";
        }

        code = sb.toString();
        if (m < 11) {
            System.out.printf("The secret is prepared: %s (0-%d).%n", stars, m - 1);
        } else if (m < 12){
            System.out.printf("The secret is prepared: %s (0-9, a).%n", stars);
        } else {
            System.out.printf("The secret is prepared: %s (0-9, a-%c).%n", stars, symbols.charAt(m - 1));
        }
        System.out.println("Okay, let's start a game!");
        return new String[] {code, Integer.toString(m), symbols};
    }

    static String generateLengthSecretCode() {
        System.out.println("Please, enter the secret code's length:");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String code = "";
        StringBuilder sb = new StringBuilder(code);
        long pseudoRandomNumber = System.nanoTime();
        long digit = pseudoRandomNumber % 10;

        if (n > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough" +
                    " unique digits.", n);
            System.exit(0);
        }

        // Get first digit that is not 0
        while (sb.length() < 1) {
            pseudoRandomNumber /= 10;
            while (digit == 0) {
                pseudoRandomNumber /= 10;
                digit = pseudoRandomNumber % 10;
            }
            sb.append(digit);
            pseudoRandomNumber /= 10;
        }

        // Append one digit at a time
        while (sb.length() < n) {
            if (pseudoRandomNumber == 0) {
                pseudoRandomNumber = System.nanoTime();
            }
            digit = pseudoRandomNumber % 10;
            // If digit is not already present in code
            if (sb.indexOf(Long.toString(digit)) == -1) {
                sb.append(digit);
            }
            pseudoRandomNumber /= 10;
        }
//        System.out.printf("The random secret number is %s.", sb.toString());
        System.out.println("Okay, let's start a game!");
        return sb.toString();
    }

    static void gradeAnswer(String[] params) {
        Scanner in = new Scanner(System.in);
        int turn = 1;
        int bull = 0;
        int cow = 0;
        String answer = "";
        String tempAns = "";
        String code = params[0];
        int m = Integer.parseInt(params[1]);
        String symbols = params[2];

        while (bull != code.length()) {
            bull = 0;
            cow = 0;
            System.out.printf("Turn %d:%n", turn);
            answer = tempAns = in.nextLine();
            if (answer.length() > code.length()) {
                System.out.printf("Error: \"%s\" isn't a valid answer.", answer);
                System.exit(0);
            }
            for (int i = 0; i < answer.length(); i++) {
                StringBuilder sb = new StringBuilder(symbols);
                if (sb.indexOf(Character.toString(answer.charAt(i))) == -1) {
                    System.out.printf("Error: \"%s\" isn't a valid answer.", answer);
                    System.exit(0);
                }
            }
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == code.charAt(i)) {
                    bull++;
                    StringBuilder sb = new StringBuilder(tempAns);
                    sb.replace(i, i + 1, "_");
                    tempAns = sb.toString();
                }
            }
            if (bull != 4) {
                for (int i = 0; i < answer.length(); i++) {
                    for (int j = 0; j < code.length(); j++) {
                        if (tempAns.charAt(i) == code.charAt(j) && i != j) {
                            cow++;
                            StringBuilder sb = new StringBuilder(tempAns);
                            sb.replace(i, i + 1, "_");
                            tempAns = sb.toString();
                        }
                    }
                }
            }
            if (cow == 1 && bull == 0) {
                System.out.printf("Grade: %d cow%n", cow);
            } else if (cow > 1 && bull == 0) {
                System.out.printf("Grade: %d cows%n", cow);
            } else if (cow == 0 && bull == 1) {
                System.out.printf("Grade: %d bull%n", bull);
            } else if (cow == 0 && bull > 1) {
                System.out.printf("Grade: %d bulls%n", bull);
            } else if (cow > 1 && bull == 1) {
                System.out.printf("Grade: %d bull and %d cows%n", bull, cow);
            } else if (cow == 1 && bull > 1) {
                System.out.printf("Grade: %d bulls and %d cow%n", bull, cow);
            } else if (cow == 1 && bull == 1) {
                System.out.printf("Grade: %d bull and %d cow%n", bull, cow);
            } else {
                System.out.printf("Grade: None.%n");
            }
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}
