import java.io.*;
import java.util.*;
public class bj_2206_2 {
    private static int n,m;
    private static int[] dx={1,-1,0,0};
    private static int[] dy={0,0,-1,1};
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n= Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        map=new int[n][m];

        for (int i = 0; i <n ; i++) {
            String str= br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j]=str.charAt(j)-'0';
            }
        }

        int result=bfs();
        System.out.println(result);

    }
    private static int bfs(){
        Queue<int[]> q=new LinkedList<>();
        boolean[][][] visited=new boolean[n][m][2];

        q.add(new int[]{0,0,1,0});// x,y,dis, broken
        visited[0][0][0]=true;

        while(!q.isEmpty()){
            int[] curr=q.poll();

            if(curr[0]==n-1 && curr[1]==m-1){
                return curr[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx=curr[0]+dx[i];
                int ny=curr[1]+dy[i];
                if(!inRange(nx,ny)) {
                    continue;
                }
                if(visited[nx][ny][curr[3]]) continue;

                if(curr[3]==0 && map[nx][ny]==1){
                    q.add(new int[]{nx,ny,curr[2]+1,1});
                }else if(map[nx][ny]==0){
                    q.add(new int[]{nx,ny,curr[2]+1,curr[3]});
                }
                visited[nx][ny][curr[3]]=true;
            }

        }
        return -1;
    }
    private static boolean inRange(int x,int y){
        return x>=0 && y>=0 && x<n && y<m;
    }
}
