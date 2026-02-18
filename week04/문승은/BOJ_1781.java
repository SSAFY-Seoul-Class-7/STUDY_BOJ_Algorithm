import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1781 {

    static int N;

    static class Problem implements Comparable<Problem>{
        int deadline;
        long cups;

        public Problem(int deadline, long cups) {
            this.deadline = deadline;
            this.cups = cups;
        }

        @Override
        public int compareTo(Problem o) {
            if(this.deadline == o.deadline) {
                return Long.compare(this.cups, o.cups);
            }
            return Integer.compare(this.deadline, o.deadline);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Problem> pq = new PriorityQueue<>();
        PriorityQueue<Problem> ans = new PriorityQueue<>((a, b) -> Long.compare(a.cups, b.cups));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            pq.add(new Problem(d,c));
        }

        while(!pq.isEmpty()) {
            Problem cur = pq.poll();

            if(ans.size() < cur.deadline) {
                ans.add(cur);
            } else {
                Problem p = ans.poll();

                if(p.cups < cur.cups) {
                    ans.add(cur);
                } else {
                    ans.add(p);
                }
            }
        }

        long total = 0;
        while(!ans.isEmpty()) {
            total += ans.poll().cups;
        }


        System.out.println(total);


    }
}
