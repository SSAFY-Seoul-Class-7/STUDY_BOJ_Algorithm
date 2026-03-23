import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230 {

    static int[] nums;
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);


        int ans = find();
        System.out.println(ans);


    }

    static int find() {
        int start = 0;
        int end = 0;
        int diff = 0;
        int minCloseM = 2000000000;

        while(start <= end && end < N) {
            diff = nums[end] - nums[start];

            if(diff == M) return diff;
            else if(diff > M) {
                minCloseM = Integer.min(minCloseM, diff);
                start++;
            }
            else if(diff < M) end++;
        }

        return minCloseM;

    }
}
