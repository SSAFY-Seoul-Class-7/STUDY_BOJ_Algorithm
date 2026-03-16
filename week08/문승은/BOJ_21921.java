import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21921 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N ; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }


        int front = 0;
        int end = X-1;
        int total = 0;
        for (int i = 0; i < X; i++) {
            total += nums[front+i];
        }

        int maxCnt = 1;
        int maxValue = total;

        for (int i = 0; i < N-X; i++) {
            total -= nums[front++];
            total += nums[++end];

            if(total > maxValue) {
                maxCnt = 1;
                maxValue = total;
            } else if(total != 0 && total == maxValue) {
                maxCnt++;
            }
        }

        if (maxValue == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(maxValue);
            System.out.println(maxCnt);
        }

    }
}
