package correcter;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        corruptBytes();
//        decodeInput(createErrors(encodeInput(getInput())));
//        getMode();
        getHammingMode();
    }

    public static void getMode() {
        Scanner in = new Scanner(System.in);
        System.out.print("Write a mode: ");
        String mode = in.nextLine();
        if ("encode".equals(mode)) {
            encodeBits();
        } else if ("send".equals(mode)) {
            corruptBytes();
        } else if ("decode".equals(mode)) {
            decodeBits();
        } else {
            System.out.println("Error: invalid mode");
        }
    }

    public static void getHammingMode() {
        Scanner in = new Scanner(System.in);
        System.out.print("Write a mode: ");
        String mode = in.nextLine();
        if ("encode".equals(mode)) {
            hammingEncodeBits();
        } else if ("send".equals(mode)) {
            corruptBytes();
        } else if ("decode".equals(mode)) {
            hammingDecodeBits();
        } else {
            System.out.println("Error: invalid mode");
        }
    }

    public static void encodeBits() {
        try {
            FileInputStream inputStream = new FileInputStream
                    ("Error Correcting Encoder-Decoder/task/src/correcter/send.txt");
//            FileInputStream inputStream = new FileInputStream("send.txt");
            byte[] bytes = inputStream.readAllBytes();
            inputStream.close();

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%8s", Integer.toBinaryString(b)).replace(" ", "0"));
            }

            byte[] encodedBytes = new byte[(int) Math.ceil(sb.length() / 3.0)];
            int parityBit = 0;

            for (int i = 0, j = 0; i < encodedBytes.length && j < sb.length(); i++, j += 3) {
                StringBuilder sb2 = new StringBuilder();

                if (j + 1 == sb.length()) {
                    parityBit = Character.getNumericValue(sb.charAt(j));
                    sb2.append(sb.charAt(j) + "" + sb.charAt(j) + "0000" + parityBit + "" + parityBit);
                } else if (j + 2 == sb.length()) {
                    parityBit = Character.getNumericValue(sb.charAt(j)) ^ Character.getNumericValue(sb.charAt(j + 1));
                    sb2.append(sb.charAt(j) + "" + sb.charAt(j) + "" + sb.charAt(j + 1) + "" + sb.charAt(j + 1) + "00"
                            + parityBit + "" + parityBit);
                } else {
                    parityBit = Character.getNumericValue(sb.charAt(j))
                            ^ Character.getNumericValue(sb.charAt(j + 1))
                            ^ Character.getNumericValue(sb.charAt(j + 2));
                    sb2.append(sb.charAt(j) + "" + sb.charAt(j) + "" + sb.charAt(j + 1) + "" + sb.charAt(j + 1)
                            + "" + sb.charAt(j + 2) + "" + sb.charAt(j + 2) + "" + parityBit + "" + parityBit);
                }

                encodedBytes[i] = (byte) Integer.parseInt(sb2.toString(), 2);
            }

            FileOutputStream outputStream = new FileOutputStream
                    ("Error Correcting Encoder-Decoder/task/src/correcter/encoded.txt");
//            FileOutputStream outputStream = new FileOutputStream("encoded.txt");
            outputStream.write(encodedBytes);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hammingEncodeBits() {
        try {
//            FileInputStream inputStream = new FileInputStream
//                    ("Error Correcting Encoder-Decoder/task/src/correcter/send.txt");
            FileInputStream inputStream = new FileInputStream("send.txt");
            byte[] bytes = inputStream.readAllBytes();
            inputStream.close();

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%8s", Integer.toBinaryString(b)).replace(" ", "0"));
            }

            byte[] encodedBytes = new byte[(int) Math.ceil(sb.length() / 4.0)];

            for (int i = 0, j = 0; i < encodedBytes.length && j < sb.length(); i++, j += 4) {
                int p1count = 0;
                int p2count = 0;
                int p4count = 0;
                int p1 = 0;
                int p2 = 0;
                int p4 = 0;

                StringBuilder sb2 = new StringBuilder("00" + "" + sb.charAt(j) + "" + "0" + ""
                        + sb.charAt(j + 1) + "" + sb.charAt(j + 2) + "" + sb.charAt(j + 3) + "0");

                // check p1
                for (int k = 0; k < sb2.length(); k += 2) {
                    if (sb2.charAt(k) == '1') {
                        ++p1count;
                    }
                }

                if (p1count % 2 == 1) {
                    p1 = 1;
                }

                // check p2
                for (int k = 1; k < sb2.length(); k += 4) {
                    for (int m = k; m < k + 2; m++) {
                        if (sb2.charAt(m) == '1') {
                            ++p2count;
                        }
                    }
                }

                if (p2count % 2 == 1) {
                    p2 = 1;
                }

                // check p4
                for (int k = 3; k < sb2.length(); k += 8) {
                    for (int m = k; m < k + 4; m++) {
                        if (sb2.charAt(m) == '1') {
                            ++p4count;
                        }
                    }
                }

                if (p4count % 2 == 1) {
                    p4 = 1;
                }

                sb2 = new StringBuilder(p1 + "" + p2 + "" + "" + sb.charAt(j) + "" + p4 + ""
                        + sb.charAt(j + 1) + "" + sb.charAt(j + 2) + "" + sb.charAt(j + 3) + "0");

                encodedBytes[i] = (byte) Integer.parseInt(sb2.toString(), 2);
            }

//            FileOutputStream outputStream = new FileOutputStream
//                    ("Error Correcting Encoder-Decoder/task/src/correcter/encoded.txt");
            FileOutputStream outputStream = new FileOutputStream("encoded.txt");
            outputStream.write(encodedBytes);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decodeBits() {
        try {
//            FileInputStream inputStream = new FileInputStream
//                    ("Error Correcting Encoder-Decoder/task/src/correcter/received.txt");
            FileInputStream inputStream = new FileInputStream("received.txt");
            byte[] bytes = inputStream.readAllBytes();
            inputStream.close();

            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (byte b : bytes) {
                if (b < 0) {
                    sb.append(Integer.toBinaryString(b).substring(24));
                } else {
                    sb.append(String.format("%8s", Integer.toBinaryString(b)).replace(" ", "0"));
                }
            }

            int x1, x2, y1, y2, z1, z2, p1, p2;
            boolean firstPair, secondPair, thirdPair, parityPair;

            for (int i = 0; i < sb.length(); i += 8) {
                x1 = Character.getNumericValue(sb.charAt(i));
                x2 = Character.getNumericValue(sb.charAt(i + 1));
                y1 = Character.getNumericValue(sb.charAt(i + 2));
                y2 = Character.getNumericValue(sb.charAt(i + 3));
                z1 = Character.getNumericValue(sb.charAt(i + 4));
                z2 = Character.getNumericValue(sb.charAt(i + 5));
                p1 = Character.getNumericValue(sb.charAt(i + 6));
                p2 = Character.getNumericValue(sb.charAt(i + 7));

                if (x1 != x2) {
                    if ((y1 ^ z1) == p1) {
                        sb2.append(0 + "" + y1 + "" + z1);
                    } else {
                        sb2.append(1 + "" + y1 + "" + z1);
                    }
                } else if (y1 != y2) {
                    if ((x1 ^ z1) == p1) {
                        sb2.append(x1 + "" + 0 + "" + z1);
                    } else {
                        sb2.append(x1 + "" + 1 + "" + z1);
                    }
                } else if (z1 != z2) {
                    if ((x1 ^ y1) == p1) {
                        sb2.append(x1 + "" + y1 + "" + 0);
                    } else {
                        sb2.append((x1 + "" + y1 + "" + 1));
                    }
                } else if (p1 != p2) {
                    sb2.append(x1 + "" + y1 + "" + z1);
                }
            }

            int rem = sb2.length() % 8;
            if (rem != 0) {
                sb2.setLength(sb2.length() - rem);
            }
            byte[] decodedBytes = new BigInteger(sb2.toString(), 2).toByteArray();

//            FileOutputStream outputStream = new FileOutputStream
//                    ("Error Correcting Encoder-Decoder/task/src/correcter/decoded.txt");
            FileOutputStream outputStream = new FileOutputStream("decoded.txt");
            outputStream.write(decodedBytes);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hammingDecodeBits() {
        try {
//            FileInputStream inputStream = new FileInputStream
//                    ("Error Correcting Encoder-Decoder/task/src/correcter/received.txt");
            FileInputStream inputStream = new FileInputStream("received.txt");
            byte[] bytes = inputStream.readAllBytes();
            inputStream.close();

            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            for (byte b : bytes) {
                if (b < 0) {
                    sb.append(Integer.toBinaryString(b).substring(24));
                } else {
                    sb.append(String.format("%8s", Integer.toBinaryString(b)).replace(" ", "0"));
                }
            }

            byte[] decodedBytes = new byte[sb.length() / 16];

            for (int i = 0, j = 0; i < decodedBytes.length && j < sb.length(); j += 8) {
                int p1count = 0;
                int p2count = 0;
                int p4count = 0;
                int p1 = Character.getNumericValue(sb.charAt(j));
                int p2 = Character.getNumericValue(sb.charAt(j + 1));
                int p4 = Character.getNumericValue(sb.charAt(j + 3));
                boolean isValidP1 = true;
                boolean isValidP2 = true;
                boolean isValidP4 = true;

                // check p1
                for (int k = j + 2; k < j + 8; k += 2) {
                    if (sb.charAt(k) == '1') {
                        ++p1count;
                    }
                }

                if (p1count % 2 != p1) {
                    isValidP1 = false;
                }

                // check p2
                for (int k = j + 1; k < j + 8; k += 4) {
                    for (int m = k; m < k + 2; m++) {
                        if (m != j + 1) {
                            if (sb.charAt(m) == '1') {
                                ++p2count;
                            }
                        }
                    }
                }

                if (p2count % 2 != p2) {
                    isValidP2 = false;
                }

                // check p4
                for (int k = j + 3; k < j + 8; k += 8) {
                    for (int m = k; m < k + 4; m++) {
                        if (m != j + 3) {
                            if (sb.charAt(m) == '1') {
                                ++p4count;
                            }
                        }
                    }
                }

                if (p4count % 2 != p4) {
                    isValidP4 = false;
                }

                if (!isValidP1 && !isValidP2 && !isValidP4) { // index 6 is wrong
                    if (sb.charAt(j + 6) == '0') {
                        sb2.append(sb.charAt(j + 2) + "" + sb.substring(j + 4, j + 6) + "1");
                    } else {
                        sb2.append(sb.charAt(j + 2) + "" + sb.substring(j + 4, j + 6) + "0");
                    }
                } else if (!isValidP1 && !isValidP2) { // index 2 is wrong
                    if (sb.charAt(j + 2) == '0') {
                        sb2.append("1" + "" + sb.substring(j + 4, j + 7));
                    } else {
                        sb2.append("0" + "" + sb.substring(j + 4, j + 7));
                    }
                } else if (!isValidP1 && !isValidP4) { // index 4 is wrong
                    if (sb.charAt(j + 4) == '0') {
                        sb2.append(sb.charAt(j + 2) + "1" + sb.substring(j + 5, j + 7));
                    } else {
                        sb2.append(sb.charAt(j + 2) + "0" + sb.substring(j + 5, j + 7));
                    }
                } else if (!isValidP2 && !isValidP4) { // index 5 is wrong
                    if (sb.charAt(j + 5) == '0') {
                        sb2.append(sb.charAt(j + 2) + "" + sb.charAt(j + 4) + "1" + "" + sb.charAt(j + 6));
                    } else {
                        sb2.append(sb.charAt(j + 2) + "" + sb.charAt(j + 4) + "0" + "" + sb.charAt(j + 6));
                    }
                } else {
                    sb2.append(sb.charAt(j + 2) + "" + sb.substring(j + 4, j + 7));
                }

                if (sb2.length() == 8) {
                    decodedBytes[i] = (byte) Integer.parseInt(sb2.toString(), 2);
                    sb2 = new StringBuilder();
                    i++;
                }
            }

//            FileOutputStream outputStream = new FileOutputStream
//                    ("Error Correcting Encoder-Decoder/task/src/correcter/decoded.txt");
            FileOutputStream outputStream = new FileOutputStream("decoded.txt");
            outputStream.write(decodedBytes);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void corruptBytes() {
        try {
//            FileInputStream inputStream = new FileInputStream
//                    ("Error Correcting Encoder-Decoder/task/src/correcter/encoded.txt");
            FileInputStream inputStream = new FileInputStream("encoded.txt");
            byte[] bytes = inputStream.readAllBytes();
            inputStream.close();

            for (int i = 0; i < bytes.length; i++) {
                bytes[i] ^= 1 << 2;
            }

//            FileOutputStream outputStream = new FileOutputStream
//                    ("Error Correcting Encoder-Decoder/task/src/correcter/received.txt");
            FileOutputStream outputStream = new FileOutputStream("received.txt");
            outputStream.write(bytes);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(input);
        return input;
    }

    public static char[] encodeInput(String input) {
        char[] inputArr = new char[input.length() * 3];
        for (int i = 0, j = 0; i < input.length() && j < inputArr.length; i++, j += 3) {
            inputArr[j] = input.charAt(i);
            inputArr[j + 1] = inputArr[j];
            inputArr[j + 2] = inputArr[j];
        }
        System.out.println(String.valueOf(inputArr));
        return inputArr;
    }

    public static void decodeInput(char[] inputArr) {
        char[] outputArr = new char[inputArr.length / 3];
        for (int i = 0, j = 0; i <= inputArr.length - 3 && j < outputArr.length; i += 3, j++) {
            for (int m = i; m < i + 2; m++) {
                for (int n = m + 1; n < i + 3; n++) {
                    if (inputArr[m] == inputArr[n]) {
                        outputArr[j] = inputArr[m];
                        break;
                    }
                }
            }
        }
        System.out.println(String.valueOf(outputArr));
    }

    public static char[] createErrors(char[] inputArr) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789";
        int randIndex;
        char randChar;
        int maxIndex;
        int minIndex;
        int maxChar = alphabet.length();

        for (int i = 0; i <= inputArr.length - 3; i += 3) {
            maxIndex = i + 2;
            minIndex = i;
            randIndex = (int) (Math.random() * (maxIndex - minIndex + 1) + minIndex);
            randChar = alphabet.charAt((int) (Math.random() * maxChar));
            while (randChar == inputArr[randIndex]) {
                randChar = alphabet.charAt((int) (Math.random() * maxChar));
            }
            inputArr[randIndex] = randChar;
        }
        System.out.println(String.valueOf(inputArr));
        return inputArr;
    }
}
