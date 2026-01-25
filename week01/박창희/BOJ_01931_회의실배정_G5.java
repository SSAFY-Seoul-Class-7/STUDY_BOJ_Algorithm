import java.io.*;
import java.util.*;

public class BOJ_01931_회의실배정_G5 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static PriorityQueue<Time> pq = new PriorityQueue<>();


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            Time time = new Time(start, end);
            pq.add(time);
        }

        int count = 0;
        int prevEndTime = 0; 

        while (!pq.isEmpty()) {
            Time currentPq = pq.poll();
            // System.out.println("value : " + currentPq.start + " " + currentPq.end);

            if (currentPq.start >= prevEndTime) {
                // System.out.println("find!");
                count++;
                prevEndTime = currentPq.end;
            }
        }

        System.out.println(count);
        
    }

}

class Time implements Comparable<Time>{
    int start;
    int end;

    public Time(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Time o) {
        if(this.end == o.end){
            return this.start - o.start; // 종료 시간이 같으면, '시작 시간'이 빠른 순서로 정렬한다
        }
        return this.end - o.end; // 종료 시간이 빠른 순서대로 정렬한다
        
    }
}
