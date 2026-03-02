import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023 {
	static int N, M;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static boolean found = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}

		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			dfs(i, 1); // 깊이 1부터 시작 (A 한 명)
			if (found) break;
		}

		System.out.println(found ? 1 : 0);
	}

	private static void dfs(int cur, int depth) {
		// 이미 찾았거나 깊이가 5에 도달하면 종료
		if (depth == 5) {
			found = true;
			return;
		}

		visited[cur] = true;
		for (int next : adj[cur]) {
			if (!visited[next]) {
				dfs(next, depth + 1);
			}
			if (found) return; // 하위 호출에서 찾았다면 즉시 복귀
		}
		visited[cur] = false; // 백트래킹: 다른 경로를 위해 방문 표시 해제
	}
}