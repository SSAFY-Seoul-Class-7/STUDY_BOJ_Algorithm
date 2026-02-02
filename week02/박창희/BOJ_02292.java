package STUDY_BOJ_Algorithm.week02.박창희;

import java.io.*;
import java.util.*;

public class BOJ_02292 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
		
		int count = 1;
		int range = 2;

		if (N == 1) {
			System.out.print(1);
		}
 
		else {
			while (range <= N) {
				range = range + (6 * count);
				count++;
			}
			System.out.print(count);
		}
    }

}
