package 풀이중;

import java.io.*;
import java.util.*;

public class BOJ_14502 {
    static int n, m;
    static int[][] map;
    static List<Point> emptyPoints = new ArrayList<>();
    static List<Point> originalVirusPoints = new ArrayList<>();
    static Point[] newWallPoints = new Point[3];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxCnt = -1;

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    emptyPoints.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    originalVirusPoints.add(new Point(i, j));
                }
            }
        }

        // 벽을 지을 포인트 확정
        setWallPoints(0, 0);


        System.out.println(maxCnt);
    }

    private static void setWallPoints(int start, int depth) {
        if (depth == 3) {
            startSimulation();
            return;
        }

        for (int i = start; i < emptyPoints.size(); i++) {
            newWallPoints[depth] = emptyPoints.get(i);
            setWallPoints(i + 1, depth + 1);
        }
    }

    private static void startSimulation() {
        int safeAreaCnt = emptyPoints.size() - 3; // 벽이 세워질 위치 제외

        // 시뮬레이션 용 맵 생성
        int[][] copiedMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        for (Point newWallPoint : newWallPoints) {
            copiedMap[newWallPoint.x][newWallPoint.y] = 1;
        }
        Queue<Point> q = new ArrayDeque<>();

        for (Point point : originalVirusPoints) {
            q.offer(point);
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int curX = cur.x;
            int curY = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];


                if (nx >= 0 && nx < n && ny >= 0 && ny < m && copiedMap[nx][ny] == 0) {
                    copiedMap[nx][ny] = 2;
                    safeAreaCnt--;
                    q.offer(new Point(nx, ny));
                }
            }
        }

        maxCnt = Math.max(safeAreaCnt, maxCnt);
    }
}
