import java.io.*;
import java.util.*;

public class BOJ_16434 {

    static class Stage {
        int cmd, a, h;

        public Stage(int cmd, int a, int h) {
            this.cmd = cmd;
            this.a = a;
            this.h = h;
        }
    }

    static int N;
    static int attack;
    static Stage[] stages;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        attack = Integer.parseInt(st.nextToken());
        stages = new Stage[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            stages[i] = new Stage(cmd,a,h);
        }

        System.out.println(binarySearch());
    }


    static long binarySearch() {
        long start = 1;
        long end = Long.MAX_VALUE;

        while(start<end) {
            long mid =  start + (end - start) / 2;

            if(checkHp(mid)) {
                end = mid;
            }else {
                start = mid+1;
            }
        }
        return end;
    }

    static boolean checkHp(long target){
        long localAttack = attack;
        long init = target;

        for(Stage s: stages) {
            long h = s.h;
            long a = s.a;
            if(s.cmd == 1) {
                long cnt = (h-1) / localAttack ;

                if( cnt * a < target) {
                    target -= a*cnt;
                } else {
                    return false;
                }

            }else {
                localAttack += a;
                target = Long.min(init, target+h);
            }
        }

        return true;

    }
}


