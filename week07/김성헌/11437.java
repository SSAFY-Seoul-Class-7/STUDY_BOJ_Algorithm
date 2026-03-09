
import java.util.*;
import java.io.*;
public class Main {
    private static List<Integer>[] list;
    private static int[] parents;
    private static int[] depth;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n=Integer.parseInt(br.readLine());

        list=new List[n+1];
        visited=new boolean[n+1];
        depth=new int[n+1];
        parents=new int[n+1];

        for (int i = 0; i < n+1; i++) {
            list[i]=new ArrayList<>();
        }

        for (int i = 0; i <n-1 ; i++) {
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        dfs(1,0);

        int cnt=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int num=LCA(a,b);
            sb.append(num).append('\n');
        }
        System.out.println(sb);
    }
    private static void dfs(int curr, int d){
        visited[curr]=true;
        depth[curr]=d;

        for (int i = 0; i < list[curr].size(); i++) {
            int next=list[curr].get(i);
            if(!visited[next]){
                parents[next]=curr;
                dfs(next,d+1);
            }
        }
    }
    private static int LCA(int a,int b){
        while(depth[a]>depth[b]){
            a=parents[a];
        }
        while(depth[b]>depth[a]){
            b=parents[b];
        }
        while(a!=b){
            a=parents[a];
            b=parents[b];
        }

        return a;
    }
}
