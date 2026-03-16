import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16236 {
	static int N;
	static int[][] map;
	static int sharkY, sharkX, sharkSize = 2, ateCount = 0, totalTime = 0;
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static class Node {
		int y, x, dist;
		Node(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sharkY = i;
					sharkX = j;
					map[i][j] = 0; // 상어의 초기 위치는 이동 후 빈칸으로 처리
				}
			}
		}

		// 더 이상 먹을 물고기가 없을 때까지 반복
		while (true) {
			Node target = findBestFish();

			if (target == null) break; // 먹을 물고기가 없으면 종료

			// 상어 이동 및 상태 업데이트
			totalTime += target.dist;
			sharkY = target.y;
			sharkX = target.x;
			map[sharkY][sharkX] = 0; // 물고기를 먹었으므로 빈칸

			ateCount++;
			if (ateCount == sharkSize) {
				sharkSize++;
				ateCount = 0;
			}
		}

		System.out.println(totalTime);
	}

	// BFS를 통해 먹을 수 있는 물고기 중 최적의 타겟을 찾는 함수
	private static Node findBestFish() {
		Queue<Node> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		ArrayList<Node> candidates = new ArrayList<>();

		q.offer(new Node(sharkY, sharkX, 0));
		visited[sharkY][sharkX] = true;

		int minDist = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			// 이미 찾은 최소 거리보다 먼 곳은 탐색할 필요 없음
			if (cur.dist >= minDist) continue;

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) continue;

				// 이동 가능 조건: 상어 크기보다 작거나 같아야 함
				if (map[ny][nx] <= sharkSize) {
					visited[ny][nx] = true;

					// 먹기 가능 조건: 상어 크기보다 작아야 함 (0인 빈칸 제외)
					if (map[ny][nx] > 0 && map[ny][nx] < sharkSize) {
						minDist = cur.dist + 1;
						candidates.add(new Node(ny, nx, minDist));
					} else {
						q.offer(new Node(ny, nx, cur.dist + 1));
					}
				}
			}
		}

		if (candidates.isEmpty()) return null;

		// 우선순위에 따른 정렬: 1. 거리 2. 위(y) 3. 왼쪽(x)
		Collections.sort(candidates, (o1, o2) -> {
			if (o1.dist != o2.dist) return o1.dist - o2.dist;
			if (o1.y != o2.y) return o1.y - o2.y;
			return o1.x - o2.x;
		});

		return candidates.get(0);
	}
}