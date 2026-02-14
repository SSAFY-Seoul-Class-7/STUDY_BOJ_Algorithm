package week04.박창희;

import java.io.*;
import java.util.*;

public class BOJ_01504 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, E;
    static List<List<Node>> graph;
    static final int INF = 1000000000;

    // 1번 정점에서 N번 정점으로 이동
    // 그 사이에 2 3 을 거침
    // 첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

      // u와 v를 시작점으로 한 결과 활용
        int[] distFromU = dijkstra(u);
        int[] distFromV = dijkstra(v);

        // 경로 1: 1 -> u -> v -> N
        long path1 = (long)distFromU[1] + distFromU[v] + distFromV[N];
        // 경로 2: 1 -> v -> u -> N
        long path2 = (long)distFromV[1] + distFromV[u] + distFromU[N];

        long minPath = Math.min(path1, path2);

        if (minPath >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(minPath);
        }
       
    }

    static int[] dijkstra (int start){

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int currentTo = currentNode.to;
            int currentWeight = currentNode.weight;

            if (currentWeight > dist[currentTo]  ) {
                continue;
            }

            for(Node node : graph.get(currentTo)){
                if (dist[node.to] > currentWeight + node.weight) {
                    dist[node.to] = currentWeight + node.weight;
                    pq.offer(new Node(node.to, dist[node.to]));
                }
            }
        }


        return dist;
    }

}

class Node implements  Comparable<Node> {
    int to;
    int weight;

    public Node(int to, int weight){
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

}
