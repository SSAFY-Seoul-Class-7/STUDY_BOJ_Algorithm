import java.io.*;
import java.util.*;


public class BOJ_1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine()); // 크레인의 개수
        Queue<Crane> q = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            q.offer(new Crane(Integer.parseInt(st.nextToken()), 0));
        }

        int m = Integer.parseInt(bf.readLine()); // 박스 개수
        List<Integer> boxes = new ArrayList<>(); // 박스의 무게를 저장
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < m; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        boxes.sort(Comparator.reverseOrder()); // 내림차순 정렬

        int s = 0;
        // 각 크레인이 모든 시행에서, 자신이 들 수 있는 가장 무거운 박스를 든다!
        while (!q.isEmpty()) { // 크레인이 빌 때 까지
            Crane crane = q.poll();
            boolean ck = false; // 이 크레인이 들었는가 안들었는가
            for (int i = 0; i < boxes.size(); i++) {
                if (crane.weight >= boxes.get(i)) {
                    boxes.remove(i);
                    crane.count++;
                    ck = true;
                    break;
                }
            }
            if (ck) q.offer(crane); // 들었던 크레인은 다시 큐에 offer
            if (boxes.isEmpty()) break;
        }

        if (q.isEmpty()) {
            System.out.println(-1); // 크레인의 최대 무게보다 박스 최대 무게가 더 무거울 경우
            return;
        }
        int maxCount = Integer.MIN_VALUE;
        for (Crane crane : q) {
            maxCount = Math.max(maxCount, crane.count);
        }

        System.out.println(maxCount);

    }

    static class Crane {
        int weight;
        int count; // 일 한 횟수

        public Crane(int weight, int count) {
            this.weight = weight;
            this.count = count;
        }
    }
}


