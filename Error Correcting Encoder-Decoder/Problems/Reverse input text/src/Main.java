import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        StringBuilder sb = new StringBuilder();
        int charAsNumber = reader.read();
        while (charAsNumber != -1 && charAsNumber != 10) {
            sb.append((char) charAsNumber);
            charAsNumber = reader.read();
        }
        reader.close();
        System.out.println(sb.reverse().toString());
    }
}