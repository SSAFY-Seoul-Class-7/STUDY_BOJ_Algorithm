package 풀이중;

import java.io.*;
import java.util.*;

public class 박스채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long l = Long.parseLong(st.nextToken());
        long w = Long.parseLong(st.nextToken());
        long h = Long.parseLong(st.nextToken());

        int n = Integer.parseInt(br.readLine());
        int[] cubes = new int[20];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            cubes[t] = cnt;
        }

        long totalUsed = 0; // 사용한 큐브의 총 개수
        long filled = 0; // 현재 크기 기준 채워진 칸 수

        for (int i = 19; i >= 0; i--) {
            filled *= 8; // 채워진 부피 현재 단위로 환산

            long curSize = 1L << i; // 현재 크기

            // 최대 수용량
            long max = (l / curSize)
                    * (w / curSize)
                    * (h / curSize);

            long empty = max - filled;
            long useCount = Math.min(empty, cubes[i]);

            filled += useCount;
            totalUsed += useCount;
        }

        // 검증
        long vol = l * w * h;

        if (filled == vol) {
            System.out.println(totalUsed);
        } else {
            System.out.println("-1");
        }
    }
}