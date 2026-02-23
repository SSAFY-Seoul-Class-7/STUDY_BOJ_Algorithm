
import java.io.*;
import java.util.*;

public class BOJ_01074 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, r, c;
    static int count = 0; // 방문 순서를 누적할 변수

    public static void main(String[] args) throws IOException {
        init();

        // 배열의 한 변의 길이: 2^N
        int size = (int) Math.pow(2, N);

        findZ(size, r, c);

        // 결과 출력
        System.out.println(count);
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    // 분할 정복 메서드
    static void findZ(int size, int r, int c) {
        // base condition: 크기가 1x1이 되면 탐색 종료
        if (size == 1) {
            return;
        }

        int half = size / 2;
        int area = half * half; // 한 사분면의 칸 수 (넓이)

        // 1사분면 (왼쪽 위)
        if (r < half && c < half) {
            findZ(half, r, c);
        }
        // 2사분면 (오른쪽 위)
        else if (r < half && c >= half) {
            count += area; // 1사분면 칸 수만큼 스킵
            findZ(half, r, c - half);
        }
        // 3사분면 (왼쪽 아래)
        else if (r >= half && c < half) {
            count += area * 2; // 1, 2사분면 칸 수만큼 스킵
            findZ(half, r - half, c);
        }
        // 4사분면 (오른쪽 아래)
        else {
            count += area * 3; // 1, 2, 3사분면 칸 수만큼 스킵
            findZ(half, r - half, c - half);
        }
    }
}