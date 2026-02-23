import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {
    static int N,M,K;
    static long[] nums;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N];
        tree = new long[N*4]; //need to find size

        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        makeTree(0,N-1,1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(cmd == 1) {
                modify(0,N-1,1,b-1,c);
                nums[b-1] = c;
            }else {
                sb.append(getSum(0,N-1,b-1,c-1,1)).append("\n");
            }
        }

        System.out.println(sb);

    }

    static long makeTree(int start, int end, int node) {

        if(start == end) {
            return tree[node] = nums[start];
        }

        int mid = (start+end)/2;
        return tree[node] = makeTree(start,mid,node*2)+ makeTree(mid+1,end,node*2+1);

    }

    static long getSum(int start, int end, int left, long right, int node) {

        if(left > end || right < start) {
            return 0;
        }

        if(right >= end && left <= start) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return getSum(start,mid,left,right,node*2) + getSum(mid+1, end, left,right,node*2+1);
    }

    static void modify(int start, int end, int node, int index, long val) {
        if(index < start || index > end ) {
            return;
        }

        tree[node] += val - nums[index];
        int mid = (start+end)/2;

        if(start==end) {
            return;
        }

        modify(start, mid, node*2, index,val);
        modify(mid+1, end, node*2+1, index,val);
    }
}
