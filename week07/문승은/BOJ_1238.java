import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1238 {

    static int N,M,X;
    static List<Edge>[] graph, igraph;
    static class Edge implements Comparable<Edge> {
        int to, price;

        public Edge(int to, int price) {
            this.to = to;
            this.price = price;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.price, o.price);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        igraph = new ArrayList[N+1];

        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
            igraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, price));
            igraph[to].add(new Edge(from,price));
        }
        //input

        //가는길 => 역방향
        int[] goDist = new int[N+1];
        Arrays.fill(goDist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(X,0));
        goDist[X] = 0;

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(cur.price > goDist[cur.to]) continue;

            for(Edge next: igraph[cur.to]) {
                int nextDist = goDist[cur.to] + next.price ;
                if(goDist[next.to] > nextDist) {
                    goDist[next.to] = nextDist;
                    pq.add(new Edge(next.to, nextDist));
                }
            }
        }

        //돌아오는 길
        int[] backDist = new int[N+1];
        Arrays.fill(backDist, Integer.MAX_VALUE);

        pq = new PriorityQueue<>();
        pq.add(new Edge(X, 0));
        backDist[X] = 0;

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(cur.price > backDist[cur.to]) continue;

            for(Edge next: graph[cur.to]) {
                int nextDist = backDist[cur.to] + next.price ;
                if(backDist[next.to] > nextDist) {
                    backDist[next.to] = nextDist;
                    pq.add(new Edge(next.to, nextDist));
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i < N+1; i++) {
            if(i == X || backDist[i] == Integer.MAX_VALUE || goDist[i] == Integer.MAX_VALUE) continue;
            if(maxTime < goDist[i]+backDist[i]){
                maxTime = goDist[i]+backDist[i];
            };
        }

        System.out.println(maxTime);

    }
}
