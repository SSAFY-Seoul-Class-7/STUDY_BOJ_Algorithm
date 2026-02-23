import java.util.*;
import java.io.*;

public class bj_1493 {
    private static long len,wid,hei;
    private static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        len=Integer.parseInt(st.nextToken());
        wid=Integer.parseInt(st.nextToken());
        hei=Integer.parseInt(st.nextToken());

        int n =Integer.parseInt(br.readLine());
        arr=new long[20];
        int cnt=0;

        for (int i = 0; i <n; i++) {
            st=new StringTokenizer(br.readLine());
            int index=Integer.parseInt(st.nextToken());
            arr[index]=Integer.parseInt(st.nextToken());
        }

        long total=0;
        for (int i = n-1; i >= 0; i--) {
            total*=8;

            long targetLen= len/(int)Math.pow(2,i);
            long targetWid= wid/(int)Math.pow(2,i);
            long targetHei= hei/(int)Math.pow(2,i);

            long possible=targetLen*targetWid*targetHei; // 현재 블럭으로 채울 수 있는 갯수

            long currPossible=possible-total;

            //가능한 공간이 블럭 갯수보다 크다면
            if(currPossible>arr[i]){
                total+=arr[i];
                cnt+=arr[i];
            }else{
                total+=currPossible;
                cnt+=currPossible;
            }
        }

        if(total== len*wid*hei) System.out.println(cnt);
        else System.out.println(-1);


    }
}
