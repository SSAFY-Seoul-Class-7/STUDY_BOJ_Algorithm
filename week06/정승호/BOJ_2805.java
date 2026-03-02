import java.io.*;
import java.util.*;

public class BOJ_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        long rt = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] >= rt) rt = arr[i];
        }
        long lt = 0;
        while (lt <= rt) {
            long mid = (lt + rt) / 2;
            long sum = 0;

            for (int i = 0; i < n; i++) {
                if (arr[i] > mid) sum += arr[i] - mid;
            }
            if (sum >= m) {
                lt = mid + 1;
            } else if (sum < m) {
                rt = mid - 1;
            }
        }
        System.out.println(lt - 1);
    }
}
