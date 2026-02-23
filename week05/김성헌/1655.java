import java.util.*;
import java.io.*;
public class bj_1655 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> a = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> b = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        int n=Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp=Integer.parseInt(br.readLine());
            if(i==0){
                a.add(tmp);
                sb.append(tmp).append('\n');
                continue;
            }

            if(i%2==1){
                b.add(tmp);
            }else {
                a.add(tmp);
            }

            if(a.peek()>b.peek() ){
                int numA=a.poll();
                int numB=b.poll();
                a.add(numB);
                b.add(numA);
            }
            sb.append(a.peek()).append('\n');

        }
        System.out.println(a);
        System.out.println(b);
        System.out.println(sb);

    }
}
