import java.io.*;
import java.util.*;

public class BOJ_15686 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    // 가장 수익을 많이 낼 수 있는  치킨집의 개수는 최대 M개

    static int[][] map;
    // 1 은 집
    // 2 는 치킨집

    static boolean[] open; // 어떤 치킨집을 선택했는지 체크

    static List<Node> BBQ;
    static List<Node> home;

    static int minDist = Integer.MAX_VALUE;
    static int[][] dists;


    //  도시의 치킨 거리는 모든 집의 치킨 거리의 합


    public static void main(String[] args) throws IOException {
        init();
        solve();
        
    }

    static void solve(){
        DFS(0, 0);
        System.out.println(minDist);
    }

    static void DFS(int start, int cnt){
        if (cnt == M) {
            int totalDist = calculateCityDist();
            minDist = Math.min(minDist, totalDist);
            return;
        }

        for (int i = start; i < BBQ.size(); i++) {
            open[i] = true;
            DFS(i + 1, cnt + 1);
            open[i] = false;
        }
    }

    static int returnDist(Node A, Node B){
        return Math.abs(A.y - B.y) + Math.abs(A.x - B.x);
    }

    static int calculateCityDist() { // 최단거리
    int sum = 0;
    for (int i = 0; i < home.size(); i++) { // 집의 인덱스 i 
        int tempMin = Integer.MAX_VALUE;
        for (int j = 0; j < BBQ.size(); j++) {
            if (open[j]) { // 선택된 치킨집만 계산
               tempMin = Math.min(tempMin, dists[i][j]);
            }
        }
        sum += tempMin;
    }
    return sum;
}

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        BBQ = new ArrayList<>();
        home = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;

                if (value == 2) {
                    BBQ.add(new Node(i, j));
                }else if (value == 1) {
                    home.add(new Node(i, j));
                }

            }
        }

        open = new boolean[BBQ.size()]; // 열린 치킨집


        //
        dists = new int[home.size()][BBQ.size()];
    
        for (int i = 0; i < home.size(); i++) {
            for (int j = 0; j < BBQ.size(); j++) {
                dists[i][j] = returnDist(home.get(i), BBQ.get(j));
            }
        }

    }

    static class Node {
        int y;
        int x;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
        
    }
    
}
