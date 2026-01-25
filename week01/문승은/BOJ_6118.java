import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_6118 {

    static int N,M;
    static List<Edge>[] graph;

    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge (int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from =  Integer.parseInt(st.nextToken());
            int to =  Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, 1));
            graph[to].add(new Edge(from, 1));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Edge(1,0));
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Edge curr = pq.poll();

            if(curr.weight > dist[curr.to]) continue;

            for(Edge next: graph[curr.to]) {
                int nextWeight = next.weight + dist[curr.to];
                if(dist[next.to] > nextWeight) {
                    dist[next.to] = nextWeight;
                    pq.add(new Edge(next.to, nextWeight));
                }
            }
        }


        int ans = -1;
        int idx = 0;
        int cnt = 0;
        for(int i=1; i<= N; i++) {
            if(ans < dist[i] &&  dist[i] != Integer.MAX_VALUE) {
                idx = i;
                ans = dist[i];
                cnt = 1;
            } else if (ans == dist[i]) {
                cnt++;
            }
        }

        System.out.println(idx + " " + ans + " " + cnt);

    }
}
