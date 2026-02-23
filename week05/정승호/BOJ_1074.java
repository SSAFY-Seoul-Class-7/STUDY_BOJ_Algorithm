package 분할정복;

import java.util.*;
import java.io.*;

public class Z {
    static int sol;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int n = multiply(2, 2 * N);

        // 2^N x 2^N
        z(0, n, x, y);
        System.out.println(sol);
    }

    public static void z(int start, int end, int x, int y) {
        int range = end - start;
        if (range == 1) {
            sol = start;
            return;
        }
        int length = (int) Math.sqrt(range);

        int half = length / 2;

        if (x < half && y < half) { // 왼쪽 위
            z(start, start + range / 4, x, y);
        } else if (x < half) { // 오른쪽 위
            z(start + range / 4, start + range / 2, x, y - half);
        } else if (y < half) { // 왼쪽 아래
            z(start + range / 2, start + range / 4 * 3, x - half, y);
        } else { // 오른쪽 아래
            z(start + range / 4 * 3, end, x - half, y- half);
        }
    }

    public static int multiply(int x, int exp) {
        if (exp == 1) {
            return x;
        }

        int temp = multiply(x, exp / 2);
        int res = temp * temp;
        if (exp % 2 == 1) {
            res *= x;
        }

        return res;
    }
}
