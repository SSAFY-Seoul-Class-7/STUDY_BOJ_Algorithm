import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,Q;
    static List<Edge>[] graph;

    static class Edge {
        int to;
        long usado;

        public Edge(int to, long usado) {
            this.to = to;
            this.usado = usado;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long usado = Long.parseLong(st.nextToken());

            graph[from].add(new Edge(to, usado));
            graph[to].add(new Edge(from, usado));

        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            long k = Long.parseLong(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

           sb.append(bfs(v,k)).append("\n");

        }

        System.out.println(sb);
    }

    static int bfs(int v, long k) {
        int ans = 0;

        Queue<Edge> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];

        q.add(new Edge(v, Long.MAX_VALUE));
        visited[v] = true;

        while(!q.isEmpty()) {
            Edge curr  = q.poll();

            if(curr.to != v && curr.usado >= k) ans++;

            for(Edge next: graph[curr.to]) {
                if(visited[next.to]) continue;

                visited[next.to] = true;
                q.add(new Edge(next.to, Long.min(next.usado, curr.usado)));
            }
        }
        return ans;
    }
}