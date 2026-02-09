import java.util.*;
import java.io.*;

public class BOJ_2295 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 1. 집합 정렬
        Arrays.sort(arr);

        // 2. 두 수의 합(x + y) 리스트 생성 및 정렬
        int[] twoSum = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) { // 중복 허용
                twoSum[idx++] = arr[i] + arr[j];
            }
        }

        // 유효한 데이터만 남기고 정렬 (idx 크기만큼만)
        int[] sortedTwoSum = Arrays.copyOf(twoSum, idx);
        Arrays.sort(sortedTwoSum);

        // 3. x + y = k - z 탐색
        // 가장 큰 k를 찾아야 하므로 k는 뒤에서부터 탐색
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                int target = arr[i] - arr[j]; // k - z

                // 4. 이분 탐색으로 k - z가 twoSum에 있는지 확인
                if (Arrays.binarySearch(sortedTwoSum, target) >= 0) {
                    System.out.println(arr[i]);
                    return; // 최댓값이므로 발견 즉시 종료
                }
            }
        }
    }
}