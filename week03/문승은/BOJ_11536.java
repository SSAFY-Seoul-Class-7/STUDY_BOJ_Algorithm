import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11536 {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        String[] names = new String[N];

        for (int i = 0; i < N; i++) {
            names[i] = br.readLine();
        }

        boolean isUp = true;
        boolean isDown = true;

        for (int i = 0; i < N-1; i++) {
            if(names[i].compareTo(names[i+1]) > 0) {
                isUp = false;
            } else if (names[i].compareTo(names[i+1]) < 0) {
                isDown = false;
            }
        }
        if(!isUp && !isDown) {
            System.out.println("NEITHER");
        } else if(isUp) {
            System.out.println("INCREASING");
        } else {
            System.out.println("DECREASING");
        }

    }
}
