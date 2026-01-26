package algorithm.Simulation.BOJ_14499_주사위굴리기;
// BOj_14499

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int M;
    static int x;
    static int y;
    static int K;

    static int[][] graph;
    static int[] dice = new int[7]; // 1:top, 2:bottom, 3:north, 4:south, 5:west, 6:east

    static int[] dx = { 0, 0, 0, -1, 1 }; // 1:동, 2:서, 3:북, 4:남
    static int[] dy = { 0, 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());
            move(dir);
        }
        System.out.println(sb.toString());
    }

    public static void move(int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || ny < 0 || nx >= N || ny >= M)
            return;

        rollDice(dir);
        x = nx;
        y = ny;

        if (graph[x][y] == 0) {
            graph[x][y] = dice[2];
        } else {
            dice[2] = graph[x][y];
            graph[x][y] = 0;
        }

        sb.append(dice[1]).append("\n");
    }

    public static void rollDice(int dir) {
        int d1 = dice[1], d2 = dice[2], d3 = dice[3], d4 = dice[4], d5 = dice[5], d6 = dice[6];
        switch (dir) {
            case 1: // 동
                dice[1] = d5;
                dice[2] = d6;
                dice[5] = d2;
                dice[6] = d1;
                break;
            case 2: // 서
                dice[1] = d6;
                dice[2] = d5;
                dice[5] = d1;
                dice[6] = d2;
                break;
            case 3: // 북
                dice[1] = d4;
                dice[2] = d3;
                dice[3] = d1;
                dice[4] = d2;
                break;
            case 4: // 남
                dice[1] = d3;
                dice[2] = d4;
                dice[3] = d2;
                dice[4] = d1;
                break;
        }
    }

}
