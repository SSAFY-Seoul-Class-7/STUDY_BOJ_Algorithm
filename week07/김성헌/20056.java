
import java.io.*;
import java.util.*;

public class Main {
    private static int n,m,k;
    private static int[][] map;
    private static int[] dx={-1,-1,0,1,1,1,0,-1};
    private static int[] dy={0,1,1,1,0,-1,-1,-1};
    private static List<Fireball> fireball=new ArrayList<>();

    static class Fireball{
        int r,c,m,s,d;

        public Fireball(int r, int c, int m,int s, int d){
            this.r=r;
            this.c=c;
            this.m=m;
            this.s=s;
            this.d=d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        //map=new int[n][n];

        for (int i = 0; i < m; i++) {
            st=new StringTokenizer(br.readLine());
            int r=Integer.parseInt(st.nextToken())-1;
            int c=Integer.parseInt(st.nextToken())-1;
            int m=Integer.parseInt(st.nextToken()); //질량
            int s=Integer.parseInt(st.nextToken()); // 속력
            int d=Integer.parseInt(st.nextToken()); // 방향

            fireball.add(new Fireball(r,c,m,s,d));
        }

        for (int i = 0; i < k; i++) {
            simul();
        }
        int total=0;
        for (int i = 0; i <fireball.size() ; i++) {
            total+=fireball.get(i).m;
        }
        System.out.println(total);

    }
    private static void simul(){
        List<Fireball>[][] map=new ArrayList[n][n];
        List<Fireball> tmp=new ArrayList<>();

        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j]=new ArrayList<>();
            }
        }

        for (int i = 0; i < fireball.size(); i++) {
            Fireball curr= fireball.get(i);
            int nx = (curr.r + dx[curr.d] * (curr.s % n) + n) % n;
            int ny = (curr.c + dy[curr.d] * (curr.s % n) + n) % n;

            map[nx][ny].add(new Fireball(nx,ny,curr.m,curr.s,curr.d));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j].size()==0) continue;

                if(map[i][j].size()==1){
                    tmp.add(map[i][j].get(0));
                    continue;
                }

                int sumM=0;
                int sumS=0;
                int cnt=map[i][j].size();
                boolean isEven=true;
                boolean isOdd=true;

                for (int l = 0; l < map[i][j].size(); l++) {
                    Fireball curr=map[i][j].get(l);
                    sumM+=curr.m;
                    sumS+=curr.s;

                    if(curr.d%2==0) isOdd=false;
                    else isEven=false;

                }
                int nextM=sumM/5;
                int nextS=sumS/cnt;

                if(nextM>0){
                    int[] directions = (isEven || isOdd) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};
                    for (int o = 0; o < directions.length; o++) {
                        tmp.add(new Fireball(i,j,nextM,nextS,directions[o]));
                    }
                }
            }
        }
        fireball=tmp;

    }
}
