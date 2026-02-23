import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class BOJ_2667 {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static StringTokenizer st;
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static int groupCnt, cnt;
	static List<Integer> answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		answer = new ArrayList<>();
		visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					++groupCnt;
					dfs(i, j);
					answer.add(cnt);
					cnt = 0;
				}
			}
		}

		System.out.println(groupCnt);
		Collections.sort(answer);
		for(int x : answer) {
			System.out.println(x);
		}
	}

	private static void dfs(int r, int c) {
		visited[r][c] = true;
		cnt++;

		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if(!visited[nr][nc] && map[nr][nc] == 1) {
				dfs(nr, nc);
			}
		}
	}

}