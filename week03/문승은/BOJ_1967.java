import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1967 {

    //각 노드에 대한 최대 길이 값 -> dp
    static int N, ans;
    static List<Node>[] graph;
    static int[] dp;
    static class Node {
        int idx, length;

        public Node(int idx, int length) {
            this.idx = idx;
            this.length = length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new List[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            graph[parent].add(new Node(child, length));
        }

        dp = new int[N+1];

        dfs(new Node(1, 0));

        System.out.println(ans);

    }

    static void dfs(Node node) {

        int max1 = 0;
        int max2 = 0;

        if(graph[node.idx].isEmpty()) {
            dp[node.idx] = 0;
            return;
        }
        for(Node child: graph[node.idx]) {
            dfs(child);
            int dist = dp[child.idx] + child.length;

            if (dist > max1) {
                max2 = max1;
                max1 = dist;
            } else if (dist > max2) {
                max2 = dist;
            }
        }

        dp[node.idx] = max1;
        ans = Integer.max(max1+max2, ans);
    }
}
