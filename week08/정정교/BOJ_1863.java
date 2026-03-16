import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_1863 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		Deque<Integer> stack = new ArrayDeque<>();
		int count = 0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			while (!stack.isEmpty() && stack.peek() > y) {
				stack.pop();
				count++;
			}

			if (!stack.isEmpty() && stack.peek() == y) {
				continue;
			}

			if (y > 0) {
				stack.push(y);
			}
		}

		while (!stack.isEmpty()) {
			stack.pop();
			count++;
		}

		System.out.println(count);
	}
}