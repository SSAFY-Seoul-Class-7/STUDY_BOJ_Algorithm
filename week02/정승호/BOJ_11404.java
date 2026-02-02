package 플로이드;

import java.io.*;
import java.util.*;

public class BOJ_11404 {
    static int MAX = 30_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] arr = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(arr[i], MAX);
            arr[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[from][to] = Math.min(w, arr[from][to]);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] == MAX) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(arr[i][j]).append(" ");
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

}
