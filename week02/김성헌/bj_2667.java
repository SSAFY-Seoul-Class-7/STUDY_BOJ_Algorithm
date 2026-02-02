package bj;
import java.util.*;
import java.io.*;
public class bj_2667 {
    private static ArrayList<Integer> list=new ArrayList<>();
    private static int n,num;
    private static int[][] map;
    private static  boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());

        map=new int[n][n];
        visited=new boolean[n][n];
        num=0;
        for (int i = 0; i < n; i++) {
            String str=br.readLine();
            for (int j = 0; j <n ; j++) {
                map[i][j]=str.charAt(j)-'0';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n ; j++) {
                if(map[i][j]==1 &&!visited[i][j]){
                    bfs(i,j);
                    num++;
                }
            }
        }
        Collections.sort(list);
        System.out.println(num);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    private static void bfs(int x,int y){
        Queue<int[]> q=new LinkedList<>();
        int[] dx={1,-1,0,0};
        int[] dy={0,0,1,-1};
        int cnt=1;
        q.add(new int[]{x,y,1});
        visited[x][y]=true;

        while(!q.isEmpty()){
            int[] curr=q.poll();

            for (int i = 0; i <4 ; i++) {
                int nx=curr[0]+dx[i];
                int ny=curr[1]+dy[i];

                if(nx>=0 &&nx<n && ny>=0 &&ny<n &&!visited[nx][ny] &&map[nx][ny]==1){
                    q.add(new int[]{nx,ny,curr[2]+1});
                    visited[nx][ny]=true;
                    cnt++;
                }
            }
        }
        list.add(cnt);

    }
}
