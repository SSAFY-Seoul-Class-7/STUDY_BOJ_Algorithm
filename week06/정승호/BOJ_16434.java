import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16434 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 방의 개수
        int H = Integer.parseInt(st.nextToken()); // 용사의 초기 공격력

        // 방 정보
        // t == 1면, 공격력이 a이고, 생명력이 h인 몬스터가 있음
        // t == 2면, 용사의 공격력을 a만큼, 용사의 체력을 h만큼 회복(단, 최대 HP는 넘지 않음)

        // N 번째 방에 있는 용을 쓰러트리기 위한 최소의 maxHp는??

        int[][] dungeon = new int[N][3]; // [i][0] -> type, [i][1] -> attack, [i][2] -> hp

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                dungeon[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long lt = 1; // 최저 생명력

        // max 값 설정 -> 방 개수 123,456
        // 1 ≤ ai, hi  ≤ 1,000,000
        long rt = Long.MAX_VALUE;

        long result = 0;

        while (lt <= rt) {
            long maxHp = lt + (rt - lt) / 2;
            long curHp = maxHp;
            long curAtk = H;

            boolean isClear = true;

            loop:
            for (int i = 0; i < N; i++) {
                int type = dungeon[i][0];
                int a = dungeon[i][1];
                long h = dungeon[i][2];

                switch (type) {
                    case 1:
                        long turnsToWin = (h % curAtk == 0) ? (h / curAtk) : (h / curAtk + 1);
                        long hitsTaken = turnsToWin - 1;
                        long totalDamage = hitsTaken * a;
                        curHp -= totalDamage;
                        if (curHp <= 0) {
                            isClear = false;
                            break loop;
                        }
                        break;
                    case 2:
                        curHp += h;
                        if (curHp > maxHp) curHp = maxHp;
                        curAtk += a;
                        break;
                }
            }

            if (isClear) {
                rt = maxHp - 1;
                result = maxHp;
            } else {
                lt = maxHp + 1;
            }
        }
        System.out.println(result);
    }
}
