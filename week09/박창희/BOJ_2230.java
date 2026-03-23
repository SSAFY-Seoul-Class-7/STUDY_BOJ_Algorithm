package week09.박창희;

import java.io.*;
import java.util.*;

public class BOJ_2230 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static long M;
    static long[] arr;

    // M 이상 이면서 가장 작은 차이를 출력

    // O(N) 으로 처리해야 그나마 처리 가능?

    public static void main(String[] args) throws IOException {
        init();

    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        arr = new long[N];

        for (int i = 0; i < N; i++) {
            long value = Long.parseLong(br.readLine());
            arr[i] = value;
        }

        Arrays.sort(arr);

        // System.out.println(Arrays.toString(arr));

        long result = Long.MAX_VALUE;
        int left = 0;
        int right = 0;

        while (right < N) {
            long diff = arr[right] - arr[left];

            if (diff < M) {
                right++;
            } else if (diff == M) {
                result = M;
                break;
            } else {
                result = Math.min(result, diff);
                left++;
            }
        }

        System.out.println(result);
    }
}