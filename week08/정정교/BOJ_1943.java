import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1943 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 0; t < 3; t++) {
			String line = br.readLine();
			if (line == null) break;
			int n = Integer.parseInt(line);
			int[][] coins = new int[n][2];
			int total = 0;

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				coins[i][0] = Integer.parseInt(st.nextToken());
				coins[i][1] = Integer.parseInt(st.nextToken());
				total += coins[i][0] * coins[i][1];
			}

			if (total % 2 != 0) {
				System.out.println(0);
				continue;
			}

			int half = total / 2;
			boolean[] dp = new boolean[half + 1];
			dp[0] = true;

			for (int i = 0; i < n; i++) {
				int v = coins[i][0];
				int c = coins[i][1];

				for (int j = half; j >= v; j--) {
					if (dp[j - v]) {
						for (int k = 0; k < c && j + v * k <= half; k++) {
							dp[j + v * k] = true;
						}
					}
				}
			}

			System.out.println(dp[half] ? 1 : 0);
		}
	}
}