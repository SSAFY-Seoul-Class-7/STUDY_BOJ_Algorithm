package 풀이중;

import java.io.*;
import java.util.*;

/**
 * 중량 제한
 */
public class BOJ_1939 {
    static class Edge implements Comparable<Edge> {
        int to;
        int limit;

        Edge(int to, int limit) {
            this.to = to;
            this.limit = limit;
        }

        public int compareTo(Edge o) {
            return Integer.compare(o.limit, this.limit); // 내림차순 정렬
        }
    }
    static int n, m;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
        // 한번에 이동해서 옮길 수 있는 최대 중량은?
        st = new StringTokenizer(br.readLine());

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(from, to));

    }

    static int dijkstra(int from, int to) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] limitArr = new int[n + 1];
        pq.offer(new Edge(from, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.to == to) {
                return cur.limit;
            }

            if (cur.limit < limitArr[cur.to]) {
                continue;
            }

            for (Edge next : graph[cur.to]) {
                int nextLimit = Math.min(cur.limit, next.limit);

                if (limitArr[next.to] < nextLimit) {
                    limitArr[next.to] = nextLimit;
                    pq.offer(new Edge(next.to, nextLimit));
                }
            }
        }
        return -1;
    }
}
