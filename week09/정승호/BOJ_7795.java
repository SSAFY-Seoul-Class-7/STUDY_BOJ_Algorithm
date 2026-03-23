package 풀이중;

import java.io.*;
import java.util.*;

public class BOJ_7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] predators = new int[n];
            int[] preys = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                predators[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                preys[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(predators);
            Arrays.sort(preys);

            int result = 0;
            int prey_ptr = 0;

            for (int predator_val : predators) {
                while (prey_ptr < m && predator_val > preys[prey_ptr]) {
                    prey_ptr++;
                }
                result += prey_ptr;

            }
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }
}
