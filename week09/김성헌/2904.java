import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static int[] arr, totalCnt;
    private static int[] spf = new int[1_000_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        arr=new int[n];
        totalCnt=new int[1_000_001];

        spf();

        StringTokenizer st=new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n ; i++) {
            int num=arr[i];
            while(num>1){
                int p=spf[num];
                totalCnt[p]++;
                num/=p;
            }
        }

        long maxGCD=1;
        long ansMove=0;

        for (int p = 2; p < 1_000_001 ; p++) {
            int per=totalCnt[p]/n;

            if(per>0){
                for (int i = 0; i < per; i++) {
                    maxGCD*=p;
                }

                for (int i = 0; i < n; i++) {
                    int cnt=0;
                    int tmp=arr[i];
                    while(tmp>0 && tmp%p==0){
                        cnt++;
                        tmp/=p;
                    }
                    if(cnt<per){
                        ansMove+=(per-cnt);
                    }
                }
            }
        }
        System.out.println(maxGCD+" "+ansMove);

    }
    private static void spf(){
        for (int i = 0; i < 1_000_001; i++) {
            spf[i]=i;
        }
        for (int p = 2; p*p <=1_000_000 ; p++) {
            if(spf[p]==p){
                for (int i = p*p; i <=1_000_000; i+=p) {
                    if(spf[i]==i) spf[i]=p;
                }
            }
        }
    }
}