package week09.박창희;

import java.io.*;
import java.util.*;

public class BOJ_18352 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, K, X;
    // 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X

    // 모든 도로의 거리는 1이다.

    // 최단 거리가 정확히 K인 모든 도시들의 번호를 출력하는 프로그램

    static List<List<Node>> map;
    static boolean[] visited;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        init();
        solve();

        if (result.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(result);
            for (int city : result) {
                sb.append(city).append("\n");
            }
            System.out.print(sb);
        }

    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        result = new ArrayList<>();

        map = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(new Node(b, 1)); // 단방향
        }

    }

    static void solve() {

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(X, 0));
        visited[X] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.len == K) {
                result.add(cur.city);
            }

            for (Node next : map.get(cur.city)) {
                if (!visited[next.city] && cur.len < K) {
                    visited[next.city] = true;
                    queue.offer(new Node(next.city, cur.len + 1));
                }
            }
        }
    }

    static class Node {
        int city;
        int len;

        public Node(int city, int len) {
            this.city = city;
            this.len = len;
        }

    }
}
