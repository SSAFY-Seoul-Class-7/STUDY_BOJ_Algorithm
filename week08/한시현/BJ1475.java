import java.io.*;
import java.util.*;

public class BJ1475 {
    static String N;
    static int[] count = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();

        for (int i = 0; i < N.length(); i++) {
            int num = N.charAt(i) - '0';
            count[num]++;
        }

        int sixNine = count[6] + count[9];
        count[6] = (sixNine / 2) + (sixNine % 2);
        count[9] = 0;

        int maxSets = 0;
        for (int i = 0; i < 10; i++) {
            maxSets = Math.max(maxSets, count[i]);
        }

        System.out.println(maxSets);
    }
}
