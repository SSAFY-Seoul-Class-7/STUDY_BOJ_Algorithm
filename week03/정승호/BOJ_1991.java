package 풀이중;

import java.io.*;
import java.util.*;

public class 트리순회 {
    static StringBuilder sb = new StringBuilder();
    static Node root;
    static class Node{
        char data;
        Node left;
        Node right;

        public Node(char data) {
            this.data = data;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char parentVal = st.nextToken().charAt(0);
            char leftVal = st.nextToken().charAt(0);
            char rightVal = st.nextToken().charAt(0);

            createNode(parentVal, leftVal, rightVal);
        }

        preOrder(root);
        sb.append('\n');
        inOrder(root);
        sb.append('\n');
        postOrder(root);
        System.out.println(sb);
    }

    static void preOrder(Node cur) {
        if(cur == null) return;

        sb.append(cur.data);
        preOrder(cur.left);
        preOrder(cur.right);
    }

    static void inOrder(Node cur) {
        if(cur == null) return;

        inOrder(cur.left);
        sb.append(cur.data);
        inOrder(cur.right);
    }

    static void postOrder(Node cur) {
        if(cur == null) return;

        postOrder(cur.left);
        postOrder(cur.right);
        sb.append(cur.data);
    }

    static void createNode(char parentVal, char leftVal, char rightVal){
        if (root == null) {
            root = new Node(parentVal);

            if (leftVal != '.') {
                root.left = new Node(leftVal);
            }
            if (rightVal != '.') {
                root.right = new Node(rightVal);
            }
        } else {
            findAndLink(root, parentVal, leftVal, rightVal);
        }
    }

    private static void findAndLink(Node cur, char parentVal, char leftVal, char rightVal) {
        if (cur.data == parentVal) {
            if (leftVal != '.') {
                cur.left = new Node(leftVal);
            }
            if (rightVal != '.') {
                cur.right = new Node(rightVal);
            }
        } else {
            if (cur.left != null) {
                findAndLink(cur.left, parentVal, leftVal, rightVal);
            }
            if (cur.right != null) {
                findAndLink(cur.right, parentVal, leftVal, rightVal);
            }
        }
    }


}
