import java.util.*;
import java.io.*;

public class 회의실배정 {
    static class Conference implements Comparable<Conference> {
        int start;
        int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Conference o) {
            if (this.end == o.end) {
                return Integer.compare(o.start, this.start);
            }
            return Integer.compare(this.end, o.end);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Conference> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Conference(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);

        int cnt = 0;
        int endTime = 0;
        for (Conference cur : list) {
            if (cur.start >= endTime) {
                endTime = cur.end;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
