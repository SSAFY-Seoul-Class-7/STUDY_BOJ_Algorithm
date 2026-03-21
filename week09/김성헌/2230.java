package bj;
import java.io.*;
import java.util.*;

public class bj_2230 {
    private static int n,m;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());


        arr=new int[n];


        for (int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int s=0;
        int e=0;

        int result=Integer.MAX_VALUE;

        while(e<n){
            int diff=arr[e]-arr[s];

            if(diff>=m){
                result=Math.min(diff,result);
                s++;
            }else{
                e++;
            }
            if(s>=e){
                e=s;
            }
        }
        System.out.println(result);

    }
}
