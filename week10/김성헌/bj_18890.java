import java.util.*;
import java.io.*;

public class bj_18870 {
    public static void main(String[] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        int[] arr= new int[n];
        int[] copy=new int[n];

        StringTokenizer st=new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
            copy[i]=arr[i];
        }

        //정렬
        Arrays.sort(copy);

        //중복값 제거
        Map<Integer,Integer> map = new HashMap<>();

        int rank=0;

        for (int num: copy){
            if(!map.containsKey(num)){
                map.put(num,rank);
                rank++;
            }
        }

        // 압축

        // 반뵉분 돌면서  정해진 순위 주는데 문자열로 만들기

        StringBuilder sb=new StringBuilder();
        for(int key: arr){
            int ranking = map.get(key);
            sb.append(ranking).append(' ');

        }
        System.out.println(sb);


        //마지막으로 문자열까지 같은거 비교해서 같으면 쌍으로 만들기
    }
}
