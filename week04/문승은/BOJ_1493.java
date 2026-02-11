import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1493 {

    static int L, W, H, N;
    static int[] cubes = new int[20];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <N; i++) {
            st = new StringTokenizer(br.readLine());
            cubes[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        long current = 0;
        long ans = 0;

        for (int i = 19; i >= 0 ; i--) {

            current *= 8;

            long tL = L >> i;
            long tW = W >> i;
            long tH = H >> i;

            long cnt = tL * tW * tH; //전체 상자

            long needed = cnt - current;

            long fill = Math.min(cubes[i], needed);

            current += fill;
            ans += fill;
        }

        if(current == (long) L * W * H) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }

    }


}
