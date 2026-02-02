import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1135 {

    static int N;
    static int[] inputs;
    static int[] dp;
    static List<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        tree = new List[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) {
                tree[parent].add(i);
            }
        }
        dfs(0);
        System.out.println(dp[0]);
    }

    static void dfs(int x) {

        if (tree[x].isEmpty()) {
            dp[x] = 0;
            return;
        }

        PriorityQueue<Integer> pq= new PriorityQueue<>(Collections.reverseOrder());

        for (int child: tree[x]) {
            dfs(child);
            pq.add(dp[child]);
        }

        int maxTime = 0;
        int cnt = 1;
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            maxTime = Integer.max(maxTime, cur + cnt );
            cnt++;
        }

        dp[x] = maxTime;
    }
}
