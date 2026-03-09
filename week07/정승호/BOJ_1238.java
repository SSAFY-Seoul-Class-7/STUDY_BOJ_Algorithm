package 풀이중;

import java.io.*;
import java.util.*;

/**
 * 학생들 중 가장 최단거리 왕복 이동시간이 긴 학생의 소요시간은?
 */

public class BOJ_1238 {
    static int n, m, x;

    static class Edge implements Comparable<Edge> {
        int to;
        int w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()); // X에서 열리는 파티

        List<Edge>[] graph = new ArrayList[n + 1];
        List<Edge>[] reverseGraph = new ArrayList[n + 1];

        int[] goDist = new int[n + 1];
        int[] comeDist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, w));
            reverseGraph[to].add(new Edge(from, w)); // 역방향 그래프 생성
        }

        dijkstra(goDist, graph);
        dijkstra(comeDist, reverseGraph);

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            max = Math.max(max, comeDist[i] + goDist[i]);
        }

        System.out.println(max);
    }

    static void dijkstra(int[] distArr, List<Edge>[] graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(distArr, Integer.MAX_VALUE);
        distArr[x] = 0;
        pq.offer(new Edge(x, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            for (Edge next : graph[cur.to]) {
                if (cur.w > distArr[cur.to]) {
                    continue;
                }

                if (next.w + cur.w < distArr[next.to]) {
                    distArr[next.to] = next.w + cur.w;
                    pq.offer(new Edge(next.to, next.w + cur.w));
                }
            }
        }
    }
}
