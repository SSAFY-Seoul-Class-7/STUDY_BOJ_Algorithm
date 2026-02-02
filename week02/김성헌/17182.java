package bj;

import java.util.*;
import java.io.*;

public class bj_17182_v2 {
    private static int n,k,result;
    private static int[][] map;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        map=new int[n][n];
        visited=new boolean[n];
        result=Integer.MAX_VALUE;

        for (int i = 0; i <n ; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        //플로이드로 이동 경로 최적화
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j]=Math.min(map[i][j],map[i][k]+map[k][j]);
                }
            }
        }

        visited[k]=true;
        dfs(k,0,1);
        System.out.println(result);

    }
    private static void dfs(int curr,int sumTime,int cnt){
        if(cnt==n){
            result=Math.min(result,sumTime);
        }
        for (int i = 0; i <n ; i++) {
            if(!visited[i]){
                visited[i]=true;
                dfs(i,sumTime+map[curr][i],cnt+1);
                visited[i]=false;
            }
        }
    }
}
