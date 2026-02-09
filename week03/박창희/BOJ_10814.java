package week03.박창희;

import java.io.*;
import java.util.*;

public class BOJ_10814 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static String[][] memeber;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        memeber = new String[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String age = st.nextToken();
            String name = st.nextToken();
            memeber[i][0] = age;
            memeber[i][1] = name;
        }

        Arrays.sort(memeber , new Comparator<String[]>() {
            @Override
	   	 		public int compare(String[] s1, String[] s2) {
	   	 		if(s1[0] == s2[0]) {
	   	 			return 1;
	   	 		}else {
	   	 			return Integer.parseInt(s1[0]) - Integer.parseInt(s2[0]);
	   	 		}
            }
        });

        for (int i = 0; i < N; i++) {
            System.out.println(memeber[i][0] + " " + memeber[i][1]);
        }
        
    }

}
