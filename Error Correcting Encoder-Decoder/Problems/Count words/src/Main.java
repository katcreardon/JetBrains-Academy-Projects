import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String input = reader.readLine().trim();
        reader.close();
        if (input.isEmpty()) {
            System.out.println(0);
        } else {
            String[] result = input.split("\\s+");
            System.out.println(result.length);
        }
    }
}