import java.io.*;
import java.util.*;
public class bj_32136 {
    private static int n;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        n=Integer.parseInt(br.readLine());
        arr=new int[n];


        StringTokenizer st=new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }

        //히터를 놓을 위치를 찾아야됨
        long s=0;
        long e=n-1;

        //범위를 찾기
        while(e-s>=3){
            long p1= (2*s+e)/3;
            long p2=(s+2*e)/3;

            if(getTime(p1)<=getTime(p2)){
                e=p2;
            }else{
                s=p1;
            }
        }
        //범위를 찾았으니깐 최소 지점 찾기
        //System.out.println(s+" "+e);
        long min=Long.MAX_VALUE;
        for (long i = s; i <=e ; i++) {
            min=Math.min(min,getTime(i));
        }
        System.out.println(min);

    }
    private static long getTime(long index){
        long max=0;
        for (int i = 0; i < n; i++) {
            long cost= Math.abs(i-index) * arr[i];
            max=Math.max(cost,max);

        }
        //반복문에서 최소값 처리 해줬기 때문에 total이 무조건 min값임
        return max;
    }
}
