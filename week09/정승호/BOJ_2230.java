package 풀이중;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int rt = 1;
        int diff = arr[n - 1] - arr[0];
        for (int lt = 0; lt <= rt; lt++) {
            while (rt < n && arr[rt] - arr[lt] < m) {
                rt++;
            }
            if (rt != n && arr[rt] - arr[lt] < diff) {
                diff = arr[rt] - arr[lt];
            }
        }
        System.out.println(diff);
    }
}
