import java.util.*;
import java.io.*;

public class BOJ_3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 동굴 길이(항상 짝수)
        int h = Integer.parseInt(st.nextToken()); // 동굴 높이

        int[] top_cnt = new int[h + 1]; // 종유석 (홀)의 갯수
        int[] bottom_cnt = new int[h + 1]; // 석순 (짝)의 갯수
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(bf.readLine());
            if (i % 2 == 1) {
                top_cnt[height]++;
            } else {
                bottom_cnt[height]++;
            }
        }

        int[] top_pSum = new int[h + 2];
        int[] bottom_pSum = new int[h + 2];

        for (int i = h; i >= 1; i--) {  // 뒤에서부터 더해야함
            top_pSum[i] = top_pSum[i + 1] + top_cnt[i]; //
            bottom_pSum[i] = bottom_pSum[i + 1] + bottom_cnt[i];

        }

        int min = 200_001;
        int cnt = 0;
        for (int i = 1; i <= h; i++) { // 각 높이별 파괴하는 갯수
            int destroy = top_pSum[h - i + 1] + bottom_pSum[i];
            if (destroy < min) {
                min = destroy;
                cnt = 1;
            } else if (destroy == min) {
                cnt++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(min).append(' ').append(cnt);
        System.out.println(sb);
    }
}
