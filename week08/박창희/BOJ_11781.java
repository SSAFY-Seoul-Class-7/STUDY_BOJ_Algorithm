package STUDY_BOJ_Algorithm.week08.박창희;

import java.io.*;
import java.util.*;

public class BOJ_11781 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int M;
    static long S, E;

    static List<List<Node>> map;

    static class Node implements Comparable<Node> {
        int to;
        long time;
        int isLate;

        public Node(int to, long time, int isLate) {
            this.to = to;
            this.time = time;
            this.isLate = isLate;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.time, o.time);
        }

    }

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();

    }

    static void dijkstra() {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.add(new Node(1, 0, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (dist[curr.to] < curr.time)
                continue;

            for (Node next : map.get(curr.to)) {
                long arrivalTime = calculateArrivalTime(dist[curr.to], next.time, next.isLate);

                if (dist[next.to] > arrivalTime) {
                    dist[next.to] = arrivalTime;
                    pq.add(new Node(next.to, dist[next.to], 0));
                }
            }
        }

        long maxDist = 0;
        for (int i = 1; i <= N; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }

        // 2를 곱했으므로 결과를 출력할 때 다시 2로 나눔 (.0 또는 .5 처리)
        if (maxDist % 2 == 0) {
            System.out.println(maxDist / 2);
        } else {
            System.out.println((maxDist / 2) + ".5");
        }
    }

    static long calculateArrivalTime(long now, long len, int isLate) {
        if (isLate == 0)
            return now + len;

        long arrival = now;
        long remainingDist = len;

        // 1. 정체 시작 전(now < S) 이동
        if (arrival < S) {
            long canGo = Math.min(remainingDist, S - arrival);
            arrival += canGo;
            remainingDist -= canGo;
        }

        // 2. 정체 구간 중(S <= arrival < E) 이동
        if (remainingDist > 0 && arrival < E) {
            long neededTime = remainingDist * 2; // 남은 거리를 다 가기 위해 필요한 시간
            long availableTime = E - arrival; // 정체 종료까지 남은 시간

            if (neededTime <= availableTime) {
                // 정체 종료 전에 도로 탈출 성공
                arrival += neededTime;
                remainingDist = 0;
            } else {
                // 도로 중간에 정체 종료됨
                // 정체 시간 동안 간 거리 = (E - arrival) / 2
                remainingDist -= (E - arrival) / 2;
                arrival = E;
            }
        }

        // 3. 정체 종료 후 이동
        if (remainingDist > 0) {
            arrival += remainingDist;
        }

        return arrival;
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Long.parseLong(st.nextToken()) * 2;
        E = Long.parseLong(st.nextToken()) * 2;

        map = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken()) * 2; // 거리도 2를 곱함
            int t1 = Integer.parseInt(st.nextToken()); // u -> v 정체 여부
            int t2 = Integer.parseInt(st.nextToken()); // v -> u 정체 여부

            // 양방향 인접 리스트 구성 (정체 여부를 각각 저장)
            map.get(u).add(new Node(v, w, t1));
            map.get(v).add(new Node(u, w, t2));
        }
    }
}
