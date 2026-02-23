import java.util.*;
import java.io.*;
public class bj_2638 {
    static class Edge{
        int x,y;
        public Edge(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    private static int n,m;
    private static int[] dx={1,-1,0,0};
    private static int[] dy={0,0,-1,1};
    private static boolean[][] visited;

    private static int[][] map;
    private static List<Edge> list=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        visited =new boolean[n][m];
        map=new int[n][m];
        for (int i = 0; i < n; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        int t=0;
        while(true) {

            visited=new boolean[n][m];
            //바깥 공기 찾기 visited로
            dfs(0,0);

            boolean flag = false;
            // 2. 터질 좌표들 리스트에 담기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) {
                        simul(i, j);
                        flag = true;
                    }
                }
            }
            //터질 좌표 없으면 끝
            if(!flag) break;
            //터트리기
            bomb();
            t++;

        }
        System.out.println(t);
    }
    private static void bomb(){
        for (int i = 0; i <list.size() ; i++) {
            Edge curr=list.get(i);
            map[curr.x][curr.y]=0;
        }
        list.clear();
    }
    private static void dfs(int x,int y){
        visited[x][y]=true;

        for (int i = 0; i < 4; i++) {
            int nx=x+dx[i];
            int ny=y+dy[i];
            if(inRange(nx,ny) && !visited[nx][ny] && map[nx][ny]==0){
                dfs(nx,ny);
            }
        }
    }

    private static void simul(int x,int y){
        int cnt=0;

        for (int i = 0; i <4 ; i++) {
            int nx=x+dx[i];
            int ny=y+dy[i];
            if(inRange(nx,ny)&&map[nx][ny]==0&&visited[nx][ny]){
                cnt++;
            }
        }

        if(cnt>=2){
            list.add(new Edge(x,y));
        }
    }
    private static boolean inRange(int x,int y){
        return x>=0 && x< n && y>=0 && y<m;
    }
}
