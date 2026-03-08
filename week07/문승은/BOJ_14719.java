import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719 {
    static int H,W;
    static int ans;
    static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        grid = new int[H+1][W+2];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= W; i++) {
            int h = Integer.parseInt(st.nextToken());
            for (int j = 0; j < h; j++) {
                grid[H-j][i] = 1;
            }
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if(grid[i][j-1] == 1 && grid[i][j] == 0){
                    for (int k = 0; k <= W-j; k++) {
                        if(grid[i][j+k] == 1) {
                            ans += k;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }


}
