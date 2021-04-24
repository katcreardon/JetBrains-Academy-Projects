package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        convertAnyBase();
    }

    static void lastDigitOct() {
        Scanner in = new Scanner(System.in);
        int dec = in.nextInt();

        if (dec < 8) {
            System.out.println(dec);
        } else {
            System.out.println(dec % 8);
        }
    }

    static void convertAnyBase() {
        int sourceBase = 0;
        int newBase = 0;
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            sourceBase = in.nextInt();
            if (sourceBase < 1 || sourceBase > 36) {
                System.out.println("Error: radix out of range (1 - 36)");
                System.exit(0);
            }
            in.nextLine();
        } else {
            System.out.println("Error: radix must be integer.");
            System.exit(0);
        }
        String number = in.nextLine();
        if (number == "") {
            System.out.println("Error: you must enter a number");
            System.exit(0);
        }
        if (in.hasNextInt()) {
            newBase = in.nextInt();
            if (newBase < 1 || newBase > 36) {
                System.out.println("Error: radix out of range (1 - 36)");
                System.exit(0);
            }
            in.nextLine();
        } else {
            System.out.println("Error: radix must be integer.");
            System.exit(0);
        }
        if (number.contains(".")) {
            convertFractionalNumber(number, sourceBase, newBase);
        } else if (sourceBase == 1) {
            convertFromBase1(number, newBase);
        } else if (newBase == 1) {
            convertToBase1(number, sourceBase);
        } else {
            int decimalNumber = Integer.parseInt(number, sourceBase);
            System.out.println(Integer.toString(decimalNumber, newBase));
        }
    }

    static void convertFractionalNumber(String number, int sourceBase, int newBase) {
        String[] splitNumber = number.split("\\.");
        int integer = Integer.parseInt(splitNumber[0], sourceBase);
        System.out.print(Integer.toString(integer, newBase) + ".");
        if (sourceBase == 10) {
            splitNumber[1] = "0." + splitNumber[1];
            convertDecFraction(splitNumber[1], newBase);
        } else {
            convertFractionToDec(splitNumber[1], sourceBase, newBase);
        }
    }

    static void convertDecFraction(String number, int newBase) {
        double fraction = Double.parseDouble(number);
        for (int i = 0; i < 5; i++) {
            String newFraction = Double.toString(fraction * newBase);
            String[] splitFraction = newFraction.split("\\.");
            System.out.print(Character.forDigit(Integer.parseInt(splitFraction[0]), newBase));
            fraction = Double.parseDouble("0." + splitFraction[1]);
        }
    }

    static void convertFractionToDec(String number, int sourceBase, int newBase) {
        int numDigits = number.length();
        double decimalValue = 0;
        for (int i = 0; i < numDigits; i++) {
            double num = (double) Character.getNumericValue(number.charAt(i));
            double denom = Math.pow(sourceBase, i + 1);
            decimalValue += num / denom;
        }
        convertDecFraction(Double.toString(decimalValue), newBase);
    }

    static void convertFromBase1(String number, int newBase) {
        int decimalNumber = number.length();

        if (newBase == 10) {
            System.out.println(decimalNumber);
        } else {
            System.out.println(Integer.toString(decimalNumber, newBase));
        }
    }

    static void convertToBase1(String number, int sourceBase) {
        int decimalNumber = Integer.parseInt(number, sourceBase);

        for (int i = 0; i < decimalNumber; i++) {
            System.out.print("1");
        }
    }

    static void convertDec() {
        Scanner in = new Scanner(System.in);
        int dec = in.nextInt();
        int radix = in.nextInt();

        switch (radix) {
            case 2:
                decToBin(dec);
                break;
            case 8:
                decToOct(dec);
                break;
            case 16:
                decToHex(dec);
                break;
        }
    }

    static void decToBin(int dec) {
        int quotient = dec / 2;
        int remainder = dec % 2;
        if (quotient != 0) {
            decToBin(quotient);
        } else {
            System.out.print("0b");
        }
        System.out.print(remainder);
    }

    static void decToOct(int dec) {
        int quotient = dec / 8;
        int remainder = dec % 8;
        if (quotient != 0) {
            decToOct(quotient);
        } else {
            System.out.print("0");
        }
        System.out.print(remainder);
    }

    static void decToHex(int dec) {
        int quotient = dec / 16;
        int remainder = dec % 16;
        if (quotient != 0) {
            decToHex(quotient);
        } else {
            System.out.print("0x");
        }

        switch (remainder) {
            case 10:
                System.out.print("a");
                break;
            case 11:
                System.out.print("b");
                break;
            case 12:
                System.out.print("c");
                break;
            case 13:
                System.out.print("d");
                break;
            case 14:
                System.out.print("e");
                break;
            case 15:
                System.out.print("f");
                break;
            default:
                System.out.print(remainder);
                break;
        }
    }
}
