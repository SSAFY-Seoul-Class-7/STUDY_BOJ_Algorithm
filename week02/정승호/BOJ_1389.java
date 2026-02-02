package 플로이드;

import java.io.*;
import java.util.*;

public class BOJ_1389 {
    static int n;
    static long[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new long[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(arr[i], 100_000_000);
            arr[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
            arr[b][a] = 1;
        }



        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }
        long[] kevinNums = new long[n + 1];

        long min = Long.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=n; j++) {
                kevinNums[i] += arr[i][j];
            }
            min = Math.min(min, kevinNums[i]);
        }
        for (int i = 1; i <= n; i++) {
            if (kevinNums[i] == min) {
                System.out.println(i);
                return;
            }

        }
    }

}
