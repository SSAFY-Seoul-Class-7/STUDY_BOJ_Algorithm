import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int N;
    static StringTokenizer st;
    static PriorityQueue<Node> pq1;

    static class Node implements Comparable<Node> {
        int s, e;

        Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            if(this.e == o.e) return Integer.compare(this.s, o.s);
            return Integer.compare(this.e, o.e);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq1 = new PriorityQueue<>();
        int end = 0;
        int cnt = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq1.offer(new Node(s,e));
        }

        while(!pq1.isEmpty()) {
            Node cur = pq1.poll();

            if(end <= cur.s) {
                end = cur.e;
                ++cnt;
            }
        }

        System.out.println(cnt);
    }
}