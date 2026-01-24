import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1916 {

    static  int N, M;
    static  ArrayList<Edge>[] graph;

    static class Edge implements Comparable<Edge> {
        int price, to;

        public Edge(int to, int price) {
            this.price = price;
            this.to = to;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.price, edge.price);

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, price));
        }
        //
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        //
        Queue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
//        boolean[] visited = new boolean[N+1];
        int total = 0;

        dist[start] = 0;
        pq.add(new Edge(start, 0));


        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(cur.to == end) break;

            if(dist[cur.to] < cur.price) continue;

            for(Edge next: graph[cur.to]) {
                int nextCost = next.price + dist[cur.to];
                if(dist[next.to] > nextCost) {
                    dist[next.to] = nextCost;
                    pq.add(new Edge(next.to, nextCost));
                }
            }
        }


        System.out.println(dist[end]);

    }
}
