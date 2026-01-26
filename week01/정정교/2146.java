import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int minBridge = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        // 1. 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2. 섬 번호 매기기 (Labeling)
        int islandIdx = 2; // 1은 입력값이랑 겹치므로 2부터 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) { // 방문 안 한 육지 발견
                    makeIsland(i, j, islandIdx);
                    islandIdx++;
                }
            }
        }

        // 3. 각 섬별로 최단 다리 길이 구하기 (BFS)
        // islandIdx가 2부터 시작해서 마지막 번호까지 증가했으므로 순회
        for (int i = 2; i < islandIdx; i++) {
            bfs(i);
        }

        System.out.println(minBridge);
    }

    // [Step 1] 섬 구분하기 (DFS/BFS 모두 가능, 여기선 BFS 사용)
    static void makeIsland(int x, int y, int idx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        map[x][y] = idx; // 섬 번호 부여
        visited[x][y] = true; // (주의) makeIsland용 visited가 필요한게 아니라, map 값 자체를 바꾸므로 visited 배열을 굳이 안써도 됨.
                              // 하지만 로직 명확성을 위해 map값이 1인 경우만 진입하므로 중복 방문 방지됨.

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (map[nx][ny] == 1) { // 아직 번호가 안 매겨진 육지라면
                        map[nx][ny] = idx;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // [Step 2] 최단 거리 구하기 (BFS)
    static void bfs(int islandIdx) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] distVisited = new boolean[N][N]; // 거리 계산용 방문 배열

        // 현재 섬의 모든 좌표를 큐에 넣음 (Multi-source BFS 시작)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == islandIdx) {
                    q.add(new int[]{i, j, 0}); // x, y, 거리
                    distVisited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cnt = cur[2];
            
            // 가지치기: 현재 구한 거리가 이미 찾은 최솟값보다 크면 더 볼 필요 없음
            if(cnt >= minBridge) continue; 

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    // 1. 다른 섬에 도착한 경우
                    if (map[nx][ny] != islandIdx && map[nx][ny] != 0) {
                        minBridge = Math.min(minBridge, cnt);
                        return; // BFS 특성상 가장 먼저 닿은게 최단 거리임
                    }
                    
                    // 2. 바다이고 아직 방문 안 한 경우 (다리 놓기)
                    if (map[nx][ny] == 0 && !distVisited[nx][ny]) {
                        distVisited[nx][ny] = true;
                        q.add(new int[]{nx, ny, cnt + 1});
                    }
                }
            }
        }
    }
}