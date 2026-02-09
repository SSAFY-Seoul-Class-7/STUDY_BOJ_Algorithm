package 풀이중;

import java.util.*;
import java.io.*;

public class 트리의지름 {
    static List<Edge>[] graph;
    static int n;
    static int maxDist;

    static class Edge {
        int to;
        int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }

        bfs(bfs(1));
        System.out.println(maxDist);
    }

    static int bfs(int start) {
        Queue<Edge> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        q.offer(new Edge(start, 0));
        int[] distArr = new int[n + 1];
        distArr[start] = 0;


        while (!q.isEmpty()) {
            Edge cur = q.poll();
            for (Edge next : graph[cur.to]) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    distArr[next.to] = cur.w + next.w;
                    q.offer(new Edge(next.to, cur.w + next.w));
                }
            }
        }

        int mostFarNode = -1;
        int max = -1;
        for (int i = 1; i <= n; i++) {
            if (max < distArr[i]) {
                max = distArr[i];
                mostFarNode = i;
            }
        }
        maxDist = max;
        return mostFarNode;
    }
}
