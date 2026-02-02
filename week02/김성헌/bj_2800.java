package bj;
import java.util.*;
import java.io.*;

public class bj_2800 {
    static class Pair{
        int start,end;
        public Pair(int x,int y){
            this.start=x;
            this.end=y;
        }
    }
    private static StringBuilder sb=new StringBuilder();
    private static Deque<Integer> stack;
    private static Set<String> set; // 마지막에 오름차순으로 정렬 필요
    private static List<Pair> list;
    private static boolean[] visited;
    private static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        str= br.readLine();
        sb=new StringBuilder();
        stack=new ArrayDeque<>();
        set=new HashSet<>(); // 마지막에 오름차순으로 정렬 필요
        list=new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)==')'){
                int start= stack.removeLast();
                list.add(new Pair(start,i));

            }else if(str.charAt(i)=='('){
                stack.add(i); //
            }
        }
        visited=new boolean[list.size()];//괄호쌍 갯수 만큼 판단
        dfs(0);
        List<String> result=new ArrayList<>(set);
        Collections.sort(result);
        for (int i = 0; i <result.size() ; i++) {
            sb.append(result.get(i)).append("\n");
        }
        System.out.println(sb);
    }
    private static void dfs(int depth){
        if(depth==list.size()) {
            boolean flag=false;
            for (int i = 0; i <visited.length; i++) {
                if(visited[i]) {
                    flag=true;
                    break;
                }
            }

            if(!flag) return; // 아무것도 선택 안되었다면

            StringBuilder sb2 = new StringBuilder();
            boolean[] check=new boolean[str.length()];

            for (int i = 0; i <list.size() ; i++) {
                if(visited[i]){
                    Pair curr=list.get(i);
                    check[curr.start]=true;
                    check[curr.end]=true;
                }
            }
            for (int i = 0; i < str.length(); i++) {
                if(!check[i]){
                    sb2.append(str.charAt(i));
                }
            }
            set.add(sb2.toString());
            return;
        }
        //현재 괄호쌍 제거
        visited[depth]=true;
        dfs(depth+1);

        visited[depth]=false;
        dfs(depth+1);
    }

}
