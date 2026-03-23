import java.io.*;
import java.util.*;
public class Main {
    private static boolean[][] visited;
    private static int n;
    private static int[][] map;
    private static int[] dx={1,-1,0,0};
    private static int[] dy={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n=Integer.parseInt(br.readLine());
        map= new int[n][n];


        int max=0;

        for (int i = 0; i < n; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j]= Integer.parseInt(st.nextToken());
                max=Math.max(max,map[i][j]);
            }
        }

        int result=0;
        int cnt=0;
        for (int k = 0; k <= max ; k++) {
            cnt=0;
            visited=new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]&&map[i][j]>k) {
                        bfs(i, j,k);
                        cnt++;
                    }
                }
            }
            result=Math.max(result,cnt);
        }
        System.out.println(result);
    }
    private static void bfs(int x,int y,int k){
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{x,y});
        visited[x][y]=true;

        while(!q.isEmpty()){
            int[] curr=q.poll();
            for (int i = 0; i < 4; i++) {
                int nx=curr[0]+dx[i];
                int ny=curr[1]+dy[i];

                if(nx>=0 && nx<n && ny>=0 && ny<n &&!visited[nx][ny] && map[nx][ny]>k){
                    q.add(new int[]{nx,ny});
                    visited[nx][ny]=true;
                }
            }
        }
    }
}
