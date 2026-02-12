    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.HashMap;
    import java.util.StringTokenizer;

    public class BOJ_27652 {

        static int ans;

        static class Node {
            HashMap<Character, Node> child;
            int count;

            public Node() {
                this.child = new HashMap<>();
                this.count = 0;
            }
        }

        static class Trie {
            Node root;

            public Trie() {
                this.root = new Node();
            }

            public void insert(String str) {
                Node node = this.root;

                for (char c: str.toCharArray()) {
                    node.child.putIfAbsent(c, new Node());
                    node.count++;
                    node = node.child.get(c);
                }
                node.count++;

            }

            public int[] search(String str) {
                Node node = this.root;
                int[] idxs = new int[str.length()];

                for (int i=0; i< str.length(); i++) {
                    char c = str.charAt(i);
                    if(!node.child.containsKey(c)) {
                        break;
                    }
                    node = node.child.get(c);
                    idxs[i] += node.count;
                }
                return idxs;
            }

            public boolean delete(String str) {
                return delete(this.root, str, 0);
            }

            private boolean delete(Node node, String str, int idx) {
                char c = str.charAt(idx);

                if(!node.child.containsKey(c)) {
                    return false;
                }

                Node cur = node.child.get(c);
                idx++;
                if(idx == str.length()) {
                    if(cur.count == 0) {
                        return false;
                    }
                    cur.count--;

                    if(cur.count ==0 && cur.child.isEmpty()) {
                        node.child.remove(c);
                    }
                } else {
                    if(!this.delete(cur, str, idx)) {
                        return false;
                    }
                    cur.count--;
                    if(cur.count == 0 && cur.child.isEmpty()) {
                        node.child.remove(c);
                    }
                }
                return true;
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            Trie A = new Trie();
            Trie B = new Trie();

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                switch (st.nextToken()) {
                    case "add":
                        String set = st.nextToken();
                        String str = st.nextToken();
                        if (set.equals("A")) {
                            A.insert(str);
                        } else {
                            B.insert(new StringBuilder(str).reverse().toString());
                        }
                        break;

                    case "delete":
                        set = st.nextToken();
                        str = st.nextToken();
                        if (set.equals("A")) {
                            A.delete(str);
                        } else {
                            B.delete(new StringBuilder(str).reverse().toString());
                        }
                        break;

                    case "find":
                        ans = 0;
                        String findWord = st.nextToken();
                        int[] a = A.search(findWord);
                        int[] b = B.search(new StringBuilder(findWord).reverse().toString());

                        for (int j = 0; j < findWord.length()-1; j++) {
                                ans += a[j] * b[findWord.length()-j-2];
                        }

                        System.out.println(ans);
                        break;
                }
            }


        }
    }
