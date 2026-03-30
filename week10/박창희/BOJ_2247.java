package week10.박창희;

import java.io.*;

public class BOJ_2247 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static String[][] arrays;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arrays = new String[N][N];
        draw(0, 0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arrays[i][j]);
            }
            sb.append('\n');

        }
        System.out.print(sb);
    }

    static void draw(int x, int y, int size) {
        if (size == 1) {
            arrays[x][y] = "*";
            return;
        }

        int newSize = size / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    blank(x + newSize * i, y + newSize * j, newSize);
                } else {
                    draw(x + newSize * i, y + newSize * j, newSize);
                }
            }
        }
    }

    static void blank(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                arrays[i][j] = " ";
            }
        }
    }
}
