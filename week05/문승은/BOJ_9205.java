import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9205 {

    static class Point{
        int x,y,idx;
        public Point(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }


    static int TC, N, beer;
    static Point[] cons;
    static List<Point>[] graph;
    static boolean[] visited;

    static boolean inRange(int x, int y) {
        return x >= -32768 && y >= -32768 && x <= 32767 && y <= 32767;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {

            beer = 20;
            N = Integer.parseInt(br.readLine());
            cons = new Point[N+2];

            st = new StringTokenizer(br.readLine());
            cons[0] = new Point(0, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                cons[j+1] = new Point(j+1,Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            cons[N+1] = new Point(N+1, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));


            graph = new ArrayList[N+2];
            for (int j = 0; j < N + 2; j++) {
                graph[j] = new ArrayList<>();
            }
            //input

            for (int j = 0; j < N + 2; j++) {
                for (int k = j+1; k < N+2; k++) {
                    if(Math.abs(cons[j].x - cons[k].x) + Math.abs(cons[j].y-cons[k].y) <= 1000) {
                        graph[j].add(cons[k]);
                        graph[k].add(cons[j]);
                    }
                }
            }
            //combination
            visited = new boolean[N+2];

            if(bfs()) {
                System.out.println("happy");
            }else {
                System.out.println("sad");
            }
        }

    }

    static boolean bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.add(cons[0]);
        visited[0] = true;

        while(!q.isEmpty()){
            Point p = q.poll();

            if(p.x == cons[N+1].x && p.y == cons[N+1].y) {
                return true;
            }


            for (Point next: graph[p.idx]) {
                if(!visited[next.idx]) {
                    visited[next.idx] = true;
                    q.add(next);
                }
            }
        }

        return false;
    }

}
