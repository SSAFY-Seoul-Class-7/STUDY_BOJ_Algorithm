import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16434 {
	static int N;
	static long initAtk;
	static long[][] rooms;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		initAtk = Long.parseLong(st.nextToken());

		rooms = new long[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			rooms[i][0] = Long.parseLong(st.nextToken());
			rooms[i][1] = Long.parseLong(st.nextToken());
			rooms[i][2] = Long.parseLong(st.nextToken());
		}

		long low = 1;
		long high = (long) 1e18;
		long result = high;

		while (low <= high) {
			long mid = (low + high) / 2;
			if (canClear(mid)) {
				result = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(result);
	}

	private static boolean canClear(long maxHp) {
		long curAtk = initAtk;
		long curHp = maxHp;

		for (int i = 0; i < N; i++) {
			long type = rooms[i][0];
			long a = rooms[i][1];
			long h = rooms[i][2];

			if (type == 1) {
				long cnt = (h - 1) / curAtk;
				if (curHp <= cnt * a) return false;
				curHp -= cnt * a;
			} else {
				curAtk += a;
				curHp = Math.min(maxHp, curHp + h);
			}
		}
		return true;
	}
}