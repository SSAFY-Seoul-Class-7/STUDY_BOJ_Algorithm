
import java.io.*;
import java.util.*;

public class BOJ_01939_중량제한_G3 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N; // 섬의 갯수
    static int M; // 다리 정보 수
    static boolean[] visited;
    static long MAX;

    static int start;
    static int end;
    static ArrayList<Node>[] graph; // 인접 리스트

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            MAX = Math.max(MAX, C);

            // 양방향 그래프 추가
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        long low = 1;
        long high = MAX;
        long answer = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            
            if (BFS(mid)) { // mid 중량으로 건널 수 있으면 더 큰 중량 시도
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(answer);
    }

    public static boolean BFS(long limit) {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[N + 1];
        
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == end) {
                return true;
            }

            for (Node next : graph[cur]) {
                if (!visited[next.to] && next.weight >= limit) {
                    visited[next.to] = true;
                    queue.offer(next.to);
                }
            }
        }

        return false;
    }

    static class Node {
        int to;
        long weight;

        public Node(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
