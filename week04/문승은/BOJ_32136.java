import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_32136 {

    static int N;
    static long start, end, mid;
    static int[] cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        cows = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(twoPoint());
    }

    static long twoPoint() {
        start = 0;
        end = 150000000000L;
        long answer = 0;

        while(start <= end) {
            mid = (start + end) / 2;

            if(check(mid)) {
                answer = mid;
                end = mid-1;
            }else {
                start = mid + 1;
            }
        }
        return answer;
    }

    static boolean check(long target) {
        double maxLeft = -1e18;
        double minRight = 1e18;
        for (int i = 0; i < N; i++) {
            double startp = (double)i - ((double) target / cows[i]);
            double endp = (double)i + ((double) target / cows[i]);
            maxLeft = Math.max(maxLeft, startp);
            minRight = Math.min(minRight, endp);
        }

        return (Math.ceil(maxLeft) <= Math.floor(minRight));

    }
}
