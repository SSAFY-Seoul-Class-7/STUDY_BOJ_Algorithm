import java.util.*;
import java.io.*;
public class bj_1931{
    static class Edge implements Comparable<Edge>{
        long start;
        long end;
        public Edge(long start,long end){
            this.start=start;
            this.end=end;
        }
        @Override
        public int compareTo(Edge o){
            if(this.end==o.end){
                return Long.compare(this.start,o.start);
            }
            return Long.compare(this.end,o.end);
        }

    }
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq=new PriorityQueue<>();

        for (int i = 0; i <n ; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            long start = Integer.parseInt(st.nextToken());
            long end= Integer.parseInt(st.nextToken());

            pq.add(new Edge(start,end));
        }

        long sTime=0;
        int cnt=0;
        while(!pq.isEmpty()){
            Edge curr=pq.poll();
            if(sTime<=curr.start){

                cnt++;
                sTime=curr.end;

            }
        }
        System.out.println(cnt);
    }
}
