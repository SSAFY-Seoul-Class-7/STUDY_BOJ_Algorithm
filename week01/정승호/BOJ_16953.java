import java.io.*;
import java.util.*;

public class BOJ_16953 {

    static class State{
        long v;
        int dist;

        public State(long v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(a, 1));
        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.v == b) {
                System.out.println(cur.dist);
                return;
            }
            if (cur.v > b) {
                continue;
            }
            q.offer(new State(cur.v * 10 + 1, cur.dist + 1));
            q.offer(new State(cur.v * 2, cur.dist + 1));

        }
        System.out.println(-1);
    }
}
