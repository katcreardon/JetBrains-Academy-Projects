package encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // String[]{alg, mode, data, String.valueOf(key), out}
        String[] arr;
        EncryptionContext context = new EncryptionContext(new ShiftAlgorithm());
        String text = "";

        if (args.length > 0) {
            arr = getInputFromCmd(args);
        } else {
            arr = getInputFromScanner();
        }

        if (arr[0].equals("unicode")) {
            context = new EncryptionContext(new UnicodeAlgorithm());
        } else if (!arr[0].equals("shift")){
            System.out.println("Error: unknown algorithm");
            System.exit(0);
        }

        if (arr[1].equals("enc")) {
            text = context.executeEncryptStrategy(arr[2], Integer.parseInt(arr[3]), arr[4]);
        } else if (arr[1].equals("dec")){
            text = context.executeDecryptStrategy(arr[2], Integer.parseInt(arr[3]), arr[4]);
        } else {
            System.out.println("Error: unknown mode");
            System.exit(0);
        }

        if (arr[4].isEmpty()) {
            System.out.println(text);
        } else {
            try {
                FileWriter fw = new FileWriter(arr[4]);
                fw.write(text);
                fw.close();
            } catch (IOException e) {
                System.out.println("Error: could not create file");
            }
        }
    }

    static String[] getInputFromCmd(String[] args) {
        String mode = "enc";
        int key = 0;
        String data = "";
        String in = "";
        String out = "";
        String alg = "shift";
        for (int i = 0; i < args.length; i += 2) {
            if (!args[i + 1].startsWith("-")) {
                if (args[i].equals("-mode")) {
                    mode = args[i + 1];
                }
                if (args[i].equals("-key")) {
                    key = Integer.parseInt(args[i + 1]);
                }
                if (args[i].equals("-data")) {
                    data = args[i + 1];
                }
                if (args[i].equals("-in")) {
                    in = args[i + 1];
                }
                if (args[i].equals("-out")) {
                    out = args[i + 1];
                }
                if (args[i].equals("-alg")) {
                    alg = args[i + 1];
                }
            } else {
                System.out.println("Error: argument does not have value");
                System.exit(0);
            }
        }
        if (data.isEmpty() && !in.isEmpty()) {
            try {
                data = readFileAsString(in);
            } catch (IOException e) {
                System.out.println("Error: input file does not exist");
            }
        }
        return new String[]{alg, mode, data, String.valueOf(key), out};
    }

        static String[] getInputFromScanner () {
            Scanner in = new Scanner(System.in);
            String mode = in.nextLine();
            String data = in.nextLine();
            int key = in.nextInt();
            String alg = in.nextLine();

            return new String[]{alg, mode, data, String.valueOf(key), ""};
        }

        static String readFileAsString (String fileName) throws IOException {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        }
    }
