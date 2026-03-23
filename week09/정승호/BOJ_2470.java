package 풀이중;

import java.io.*;
import java.util.*;

public class BOJ_2470 {
    static class Pair {
        int value1;
        int value2;
        int absSum;

        public Pair(int v1, int v2) {
            this.value1 = v1;
            this.value2 = v2;
            this.absSum = Math.abs(v1 + v2);
        }

        public void update(int v1, int v2) {
            this.value1 = v1;
            this.value2 = v2;
            this.absSum = Math.abs(v1 + v2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int lt = 0;
        int rt = n - 1;

        Pair best = new Pair(arr[lt], arr[rt]);

        while (lt < rt) {
            int sum = arr[lt] + arr[rt];

            if (Math.abs(sum) < best.absSum) {
                best.update(arr[lt], arr[rt]);
            }

            if (sum > 0) {
                rt--;
            } else if (sum < 0) {
                lt++;
            } else {
                break;
            }
        }
        System.out.print(best.value1 + " " + best.value2);
    }
}
