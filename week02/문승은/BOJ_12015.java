import java.io.*;
import java.util.*;

public class BOJ_12015 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        List<Integer> LIS = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        LIS.add(nums[0]);
        for(int i=1; i<N;i++) {

            int key = nums[i];

            //LIS 의 마지막 값이 현재 원소보다 작으면 추가
            if(key > LIS.get(LIS.size()-1)) LIS.add(key);

            //같거나 크면 (lower bound) (이분탐색)
            else {
                int start = 0;
                int end = LIS.size()-1;

                while(start < end) {
                    int mid = (start + end) / 2;
                    if(LIS.get(mid) >= key) end = mid;
                    else start = mid+1;
                }
                LIS.set(end, key);
            }

        }

        System.out.println(LIS.size());

    }


}
