package bj;
import java.util.*;
import java.io.*;

public class bj_1238 {
    private static int n,m,x;
    private static List<Edge>[] list,reverse;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        x=Integer.parseInt(st.nextToken());

        list=new List[n+1];
        reverse=new List[n+1];

        for (int i = 1; i < n+1 ;i++) {
            list[i]=new ArrayList<>();
            reverse[i]=new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st=new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int cost=Integer.parseInt(st.nextToken());
            list[from].add(new Edge(to,cost));
            reverse[to].add(new Edge(from,cost));
        }

        int[] asc=dij(x,list);
        int[] desc=dij(x,reverse);

        int max=0;

        for (int i = 1; i <=n ; i++) {
            max=Math.max(max,asc[i]+desc[i]);
        }
        System.out.println(max);


    }
    private static int[] dij(int start,List<Edge>[] graph){
        int[] dist=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start]=0;
        PriorityQueue<Edge> pq=new PriorityQueue<>();

        pq.add(new Edge(start,0));

        while(!pq.isEmpty()){
            Edge curr=pq.poll();
            int currIndex=curr.to;
            int currCost=curr.cost;
            for (int i = 0; i < graph[currIndex].size(); i++) {
                Edge next=graph[currIndex].get(i);
                int nextCost=dist[currIndex]+next.cost;
                if(dist[next.to]>nextCost){
                    dist[next.to]=nextCost;
                    pq.add(new Edge(next.to,nextCost));
                }
            }
        }
        return dist;

    }
    static class Edge implements Comparable<Edge>{
        int to,cost;
        public Edge(int to,int cost){
            this.to=to;
            this.cost=cost;
        }
        @Override
        public int compareTo(Edge o){
            return this.cost-o.cost;
        }
    }
}
//학생 x, m개의 단방향 도로