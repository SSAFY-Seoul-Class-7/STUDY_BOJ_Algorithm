import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long L = Long.parseLong(st.nextToken());
		long W = Long.parseLong(st.nextToken());
		long H = Long.parseLong(st.nextToken());

		int N = Integer.parseInt(br.readLine());
		long[] cubes = new long[20];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			cubes[idx] = Long.parseLong(st.nextToken());
		}

		long totalUsedCubes = 0;
		long filledVolumeUnits = 0;

		// 가장 큰 큐브 크기(2^19)부터 0까지 거꾸로 확인
		for (int i = 19; i >= 0; i--) {
			// 이전 단계에서 채운 부피를 현재 크기 단위로 변환 (1개 -> 8개)
			filledVolumeUnits <<= 3;

			// 현재 크기(2^i)로 박스에 들어갈 수 있는 최대 개수 계산
			long possibleCubes = (L >> i) * (W >> i) * (H >> i) - filledVolumeUnits;

			// 내가 가진 개수와 필요한 개수 중 최소값 선택
			long countToUse = Math.min(cubes[i], possibleCubes);

			totalUsedCubes += countToUse;
			filledVolumeUnits += countToUse;
		}

		// 최종적으로 채워진 단위 부피가 전체 부피(L*W*aH)와 같은지 확인
		// i=0일 때 filledVolumeUnits는 1x1x1 크기의 개수이므로 L*W*H와 같아야 함
		if (filledVolumeUnits == L * W * H) {
			System.out.println(totalUsedCubes);
		} else {
			System.out.println(-1);
		}
	}
}