import java.io.*;
import java.util.*;

public class BOJ_2531 {

	static int[] sushi, kinds;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		sushi = new int[N];

		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		kinds = new int[D + 1];
		// init
		int cnt = 0;
		for (int i = 0; i < K; i++) {

			if (kinds[sushi[i]] == 0)
				cnt++;
			kinds[sushi[i]]++;
		}
//		kinds[C]++;
		int maxKind = cnt;

		if (kinds[C] == 0)
			maxKind = cnt + 1;
		else
			maxKind = cnt;

		// queue
		for (int i = 1; i < N; i++) {
			kinds[sushi[i - 1]]--; // pop
			if (kinds[sushi[i - 1]] == 0)
				cnt--;

			int s = sushi[(i + K - 1) % N];
			if (kinds[s] == 0)
				cnt++;
			kinds[s]++;

			int temp;
			if (kinds[C] == 0)
				temp = cnt + 1;
			else
				temp = cnt;

			maxKind = Math.max(maxKind, temp);

		}

		System.out.println(maxKind);

	}

}
