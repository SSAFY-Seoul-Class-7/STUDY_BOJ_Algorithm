import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1931_회의실배정 {

    static int N;
    static class Time implements Comparable<Time> {
        long start, finish;

        public Time(long start, long finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public int compareTo(Time t) {
            if (this.finish == t.finish) {
                return Long.compare(this.start, t.start);
            }
            return Long.compare(this.finish, t.finish);
        }

    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        PriorityQueue<Time> classes = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            classes.add(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int cnt = 0;
        long available = 0;

        while(!classes.isEmpty()) {
            Time curr = classes.poll();

            if(curr.start >= available) {
                available = curr.finish;
                cnt++;
            }
        }

        System.out.println(cnt);

    }

}
