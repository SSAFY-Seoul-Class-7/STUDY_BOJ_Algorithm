import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_1012 {

	static StringTokenizer st;
	static int[][] map;
	static int N, M, K;
	static boolean[][] visited;
	static int answer;
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			//초기화
			map = new int[N][M];
			visited = new boolean[N][M];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int b = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				map[a][b] = 1; //배추 표시
			}

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(!visited[i][j] && map[i][j] == 1) {
						++answer;
						dfs(i, j);
					}
				}
			}

			System.out.println(answer);
		}
	}

	private static void dfs(int r, int c) {
		visited[r][c] = true;

		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
			if(!visited[nr][nc] && map[nr][nc] == 1) {
				dfs(nr, nc);
			}
		}
	}
}