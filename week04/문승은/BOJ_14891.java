import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14891 {

    static int[][] wheels;
    static int[] state;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        wheels = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = line.charAt(j) - '0';
            }
        }
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int which = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            state = new int[4];
            update(dir, which);

            for (int j = 0; j < 4; j++) {
                if (state[j] == 0) continue;
                rotate(state[j], j);
            }
        }

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            if(wheels[i][0] == 0) continue;
            ans += (int) Math.pow(2, wheels[i][0] + i -1);
        }
        System.out.println(ans);
    }


    public static void update(int dir, int which) {
        state[which] = dir;

        if(which == 0) {
            if(state[1] == 0 && wheels[0][2] != wheels[1][6]) update(dir*-1, 1);
        }else if (which == 1){
            if(state[0] == 0 && (wheels[1][6] != wheels[0][2])) update(dir*-1, 0);
            if(state[2] == 0 && wheels[1][2] != wheels[2][6]) update(dir*-1, 2);
        }else if (which == 2) {
            if(state[1] == 0 && wheels[2][6] != wheels[1][2]) update(dir*-1, 1);
            if(state[3] == 0 && wheels[2][2] != wheels[3][6]) update(dir*-1, 3);
        } else {
            if(state[2] == 0 && wheels[2][2] != wheels[3][6]) update(dir*-1, 2);
        }

    }

    public static void rotate(int dir, int which) {
        if(dir == 1){
            int temp = wheels[which][7];
            for (int i = 7; i>0;  i--) {
                wheels[which][i] = wheels[which][(i+7)%8];
            }
            wheels[which][0] = temp;
        }else {
            int temp = wheels[which][0];
            for (int i = 0; i < 7; i++) {
                wheels[which][i] = wheels[which][(i+1)%8];
            }
            wheels[which][7] = temp;
        }
    }
}
