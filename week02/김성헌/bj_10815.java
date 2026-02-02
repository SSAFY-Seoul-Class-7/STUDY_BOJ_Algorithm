package bj;

import java.util.*;
import java.io.*;

public class bj_10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 상근이의 카드 입력 및 정렬
        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        // 2. 확인할 카드들 입력 및 탐색
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            // 직접 만든 이분 탐색 함수 호출
            sb.append(binarySearch(cards, target)).append(" ");
        }

        // 3. 결과 한 번에 출력
        System.out.println(sb.toString().trim());
    }

    // 이분 탐색 메서드
    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return 1; // 찾으면 1 반환
            } else if (arr[mid] > target) {
                right = mid - 1; // 중간값이 타겟보다 크면 왼쪽 절반 탐색
            } else {
                left = mid + 1;  // 중간값이 타겟보다 작으면 오른쪽 절반 탐색
            }
        }

        return 0; // 못 찾으면 0 반환
    }
}