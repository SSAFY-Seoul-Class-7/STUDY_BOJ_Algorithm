import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1943 {
    //동전 분배
    static int N;

    static class Coin {
        int price, cnt;
        public Coin(int price, int cnt) {
            this.price = price;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int tc=0; tc<3; tc++) {
            N = Integer.parseInt(br.readLine());
            Coin[] coins = new Coin[N];
            int target = 0;
            //n번째 동전으로 만들 수 있는 가격

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int price = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                coins[i] = new Coin(price, cnt);
                target += price*cnt;
            }
            if(target % 2 == 1) {
                sb.append(0).append("\n");
                continue;
            };
            target = target / 2;
            boolean[] dp = new boolean[target+1];
            dp[0] = true;

            for(int i=0; i<N;i++) {
                for(int j=target; j > -1; j--) {
                    if (dp[j]) {
                        for(int k=1; k<coins[i].cnt+1;k++) {
                            if(j+k*coins[i].price > target) continue;
                            dp[j+k*coins[i].price] = true;
                        }
                    }
                }
            }


            if (dp[target]) sb.append(1).append("\n");
            else sb.append(0).append("\n");


        }
        System.out.println(sb);

    }
}
