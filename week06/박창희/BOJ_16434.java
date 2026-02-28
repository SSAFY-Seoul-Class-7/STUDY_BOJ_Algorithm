package week06.박창희;


import java.io.*;
import java.util.*;


public class BOJ_16434 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int room;
    static long heroAtack;
    static long[][] roomInfo;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        
    }

    public static void solve(){
        long curHP = 0;    // 현재 깎인 누적 체력
        long minHP = 0;    // 지금까지 겪은 최저 체력 (가장 큰 음수)

        for (int i = 0; i < room; i++) {
            long type = roomInfo[i][0];
            long a = roomInfo[i][1];
            long h = roomInfo[i][2];

            if (type == 1) { //  몬스터가 있음
                long attackCount = (h + heroAtack - 1) / heroAtack - 1;
                curHP -= (attackCount * a);
                
                // 가장 많이 피가 깎인 순간을 갱신
                minHP = Math.min(minHP, curHP);
            }else if(type == 2){ // 포션
                heroAtack += a;
                curHP += h;
                if (curHP > 0) curHP = 0; 
            }
        }

        System.out.println(Math.abs(minHP) + 1);
    }

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine()," ");
        room = Integer.parseInt(st.nextToken());
        heroAtack = Integer.parseInt(st.nextToken());
        roomInfo = new long[room][3];

        for (int i = 0; i < room; i++) {
            st = new StringTokenizer(br.readLine()," ");
            long t = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());

            roomInfo[i][0] = t;
            roomInfo[i][1] = a;
            roomInfo[i][2] = h;

            // ti가 1인 경우 공격력이 ai이고 생명력이 hi인 몬스터가 있음을 나타내고,

            // ti가 2인 경우 
            // 용사의 공격력 HATK를 ai만큼 증가시켜주고 
            // 용사의 현재 생명력 HCurHP를 hi만큼 회복시켜주는 포션이 있음을 나타
        }
    }
}

// 3 3
// 1 1 20
// 2 3 10
// 1 3 100