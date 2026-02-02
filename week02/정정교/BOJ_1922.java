import java.io.*;
import java.util.*;

// 간선 정보를 저장할 클래스
class Edge implements Comparable<Edge> {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost; // 비용 기준 오름차순 정렬
    }
}

public class BOJ_1922 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 컴퓨터 수
        int M = Integer.parseInt(br.readLine()); // 간선 수

        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(from, to, cost));
        }

        // 1. 간선 비용 기준 오름차순 정렬
        Collections.sort(edgeList);

        // 2. Union-Find를 위한 부모 배열 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int totalCost = 0;
        int count = 0;

        // 3. 간선을 하나씩 확인하며 MST 구성
        for (Edge edge : edgeList) {
            // 사이클이 발생하지 않는 경우에만 선택
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                totalCost += edge.cost;
                count++;
            }

            // 모든 컴퓨터가 연결되면(간선 수 = N-1) 조기 종료 가능
            if (count == N - 1) break;
        }

        System.out.println(totalCost);
    }

    // 부모 노드를 찾는 find 연산 (경로 압축 적용)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 두 집합을 합치는 union 연산
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }
}