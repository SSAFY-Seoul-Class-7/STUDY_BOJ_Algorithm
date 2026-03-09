import java.io.*;
import java.util.*;

public class BOJ_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] height = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int totalWater = 0;
        // 양쪽 끝은 물이 고일 수 없으므로 1부터 W-2까지 순회
        for (int i = 1; i < W - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;

            // 현재 기준 왼쪽에서 가장 높은 벽 찾기
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            // 현재 기준 오른쪽에서 가장 높은 벽 찾기
            for (int j = i; j < W; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            // 두 높은 벽 중 낮은 쪽 높이만큼 물이 찬다
            int minWall = Math.min(leftMax, rightMax);
            if (minWall > height[i]) {
                totalWater += (minWall - height[i]);
            }
        }
        System.out.println(totalWater);
    }
}