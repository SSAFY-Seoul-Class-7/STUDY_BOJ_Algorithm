
import java.io.*;
import java.util.*;

public class BOJ_01493 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int length, width, height;
    static int type;
    static int[] cubes = new int[20];

    static long answer = 0;
    static boolean isPossible = true;

    public static void main(String[] args) throws IOException {
        init();
        solve(length, width, height);

        if (isPossible) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        type = Integer.parseInt(br.readLine());

        for (int i = 0; i < type; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            cubes[num] = count;
        }
    }

    static void solve(long l, long w, long h) {
        if (l == 0 || w == 0 || h == 0)
            return;

        boolean placed = false;

        for (int i = 19; i >= 0; i--) {
            if (cubes[i] == 0)
                continue;

            long S = 1L << i;
            if (S <= l && S <= w && S <= h) {
                cubes[i]--;
                answer++;
                placed = true;

                solve(l - S, S, S);
                solve(l, w - S, S);
                solve(l, w, h - S);

                return;
            }
        }

        if (!placed) {
            isPossible = false;
        }
    }
}
