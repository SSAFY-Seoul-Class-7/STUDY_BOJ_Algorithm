package week04.박창희;

import java.io.*;
import java.util.*;

public class BOJ_05719 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M; // 장소수 도로수
    static int S, D; // 시작점과 도착점

    static List<List<Node>> graph;
    static List<Integer>[] parent;
    static final int INF = Integer.MAX_VALUE;
    static boolean[][] isRemoved;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M ==0) {
                break;
            }

            st = new StringTokenizer(br.readLine(), " ");
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            parent = new ArrayList[N];
            isRemoved = new boolean[N][N];

            for (int i = 0; i < N; i++){
                parent[i] = new ArrayList<>();
            } 

            for (int i = 0; i < N; i++) {
                graph.add(new ArrayList<>()); 
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                graph.get(u).add(new Node(v, p));
            }

            dijkstra(S);

            removeShortestPaths(D);

            int result = dijkstra(S);

            System.out.println(result == INF ? -1 : result);
        }
       
        
    }

    static int dijkstra(int start) {
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currentCost = curr.cost;
            int currentIndex = curr.index;

            if(dist[currentIndex] < currentCost ){
                continue;
            }

            for (Node next : graph.get(currentIndex)) {
                if (isRemoved[currentIndex][next.index]) continue;

                if (dist[next.index] > dist[currentIndex] + next.cost) {
                    dist[next.index] = dist[currentIndex] + next.cost;
                    pq.offer(new Node(next.index, dist[next.index]));
                    parent[next.index].clear(); // 더 짧은 경로를 찾았으니 기존 부모들은 무효!
                    parent[next.index].add(currentIndex); // 새 부모 추가
                }else if (dist[next.index] == dist[currentIndex] + next.cost) {
                    parent[next.index].add(currentIndex); // 거리가 같다면 이 경로도 최단 경로 후보!
                }
            }
        }

        return dist[D];
    }
    static void removeShortestPaths(int destination) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(destination);
        boolean[] visited = new boolean[N]; // 노드 중복 방문 방지용
        visited[destination] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int prev : parent[curr]) {
                isRemoved[prev][curr] = true;
                if (!visited[prev]) {
                    visited[prev] = true;
                    q.offer(prev);
                }
            }
        }

    }
}

class Node implements Comparable<Node> {
    int index;
    int cost;

    public Node(int index, int cost){
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost; // 오름차순 정렬
    }
    
}
