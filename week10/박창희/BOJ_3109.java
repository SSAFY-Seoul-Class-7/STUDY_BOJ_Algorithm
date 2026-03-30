package week10.박창희;

import java.io.*;
import java.util.*;

public class BOJ_3109 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R;
    static int C;

    static int[] plus = { -1, 0, 1 }; // 오위 / 오 / 오아래

    static char[][] pipeLine;

    static int count;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        pipeLine = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                pipeLine[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            if (DFS(0, i)) {
                count++;
            }
        }

        System.out.println(count);

    }

    static boolean DFS(int x, int y) {
        pipeLine[y][x] = 'x';
        if (x == C - 1)
            return true;
        for (int i = 0; i < 3; i++) {
            int dx = x + 1;
            int dy = y + plus[i];
            if (dx <= C && dy >= 0 && dy < R && pipeLine[dy][dx] == '.') {
                if (DFS(dx, dy))
                    return true;
            }
        }
        return false;
    }
}
