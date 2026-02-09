import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1010 {

    static int T,N,M;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        dp = new int[30][30];

        for (int j = 0; j < 30; j++) {
            dp[j][j] = 1;
            dp[j][0] = 1;
        }

        for (int j = 2; j < 30; j++) {
            for (int k = 1; k < 30; k++) {
                dp[j][k] = dp[j-1][k-1] + dp[j-1][k];
            }
        }

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());


            System.out.println(dp[M][N]);
        }
    }


}
