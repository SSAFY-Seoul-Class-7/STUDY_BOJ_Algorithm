import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] truthKnowers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 파티 수

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        // 진실을 아는 사람들 입력
        st = new StringTokenizer(br.readLine());
        int truthCount = Integer.parseInt(st.nextToken());

        // 진실을 아는 사람이 없으면 모든 파티에서 거짓말 가능
        if (truthCount == 0) {
            System.out.println(M);
            return;
        }

        truthKnowers = new int[truthCount];
        for (int i = 0; i < truthCount; i++) {
            truthKnowers[i] = Integer.parseInt(st.nextToken());
        }

        // 파티 정보 입력 및 그룹화(Union)
        ArrayList<Integer>[] parties = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            parties[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int pSize = Integer.parseInt(st.nextToken());

            int firstPerson = Integer.parseInt(st.nextToken());
            parties[i].add(firstPerson);

            for (int j = 1; j < pSize; j++) {
                int nextPerson = Integer.parseInt(st.nextToken());
                parties[i].add(nextPerson);
                // 같은 파티에 있는 사람들을 하나의 집합으로 합침
                union(firstPerson, nextPerson);
            }
        }

        // 4. 거짓말 가능 여부 판별
        int possibleParties = 0;
        for (int i = 0; i < M; i++) {
            boolean canLie = true;
            for (int person : parties[i]) {
                // 파티 참석자 중 한 명이라도 진실 그룹에 속해 있는지 확인
                if (isLinkedToTruth(person)) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) possibleParties++;
        }

        System.out.println(possibleParties);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    // 해당 인물이 진실을 아는 사람과 같은 그룹인지 확인
    static boolean isLinkedToTruth(int person) {
        int rootPerson = find(person);
        for (int knower : truthKnowers) {
            if (rootPerson == find(knower)) return true;
        }
        return false;
    }
}