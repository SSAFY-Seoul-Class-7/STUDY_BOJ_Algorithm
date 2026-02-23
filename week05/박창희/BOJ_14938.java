
import java.io.*;
import java.util.*;

public class BOJ_14938 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m, r;
    // 지역의 개수 n (1 ≤ n ≤ 100)과 예은이의 수색범위 m (1 ≤ m ≤ 15), 길의 개수 r (1 ≤ r ≤ 100)
    static int[] items;
    static List<List<Node>> graph;

    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        solve();

    }

    static void solve() {
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dijkstra(i));
        }

        System.out.println(max);
    }

    static int dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1]; // 거리
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0)); // 거리를 구분하기 위함

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.to] < cur.weight) {
                continue;
            }

            for (Node nextNode : graph.get(cur.to)) {
                if (dist[nextNode.to] > dist[cur.to] + nextNode.weight) {
                    dist[nextNode.to] = dist[cur.to] + nextNode.weight;
                    pq.offer(new Node(nextNode.to, dist[nextNode.to]));
                }
            }
        }

        int sum = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m) {
                sum += items[i];
            }
        }

        return sum;

    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

    }
}
