package com.basic.campus001.class03;

import com.basic.campus001.Node;

import java.util.ArrayList;
import java.util.List;

public class Code02_TreeEqual {

    public static boolean containsTree1(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValue(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    private static boolean isSameValue(Node big, Node small) {
        if (big != null && small == null) {
            return false;
        }
        if (big == null && small != null) {
            return false;
        }
        if (big == null && small == null) {
            return true;
        }
        if (big.value != small.value) {
            return false;
        }
        return isSameValue(big.left, small.left) && isSameValue(big.right, small.right);
    }

    public static boolean containsTree2(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        List<String> bigPre = preSerial(big);
        List<String> smallPre = preSerial(small);
        String[] str = new String[bigPre.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = bigPre.get(i);
        }

        String[] match = new String[smallPre.size()];
        for (int j = 0; j < match.length; j++) {
            match[j] = smallPre.get(j);
        }
        return getIndexOf(str, match) != -1;
    }

    private static int getIndexOf(String[] str, String[] match) {
        if (str == null || match == null || str.length < 1 || str.length < match.length) {
            return -1;
        }
        int[] next = getNextArray(match);
        int x = 0;
        int y = 0;
        while (x < str.length && y < match.length) {
            if (isEquals(str[x], match[y])) {
                x++;
                y++;
            } else if (next[y] != -1) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == match.length ? x - y : -1;
    }

    private static boolean isEquals(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }

    private static int[] getNextArray(String[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] res = new int[match.length];
        res[0] = -1;
        res[1] = 0;
        int cn = 0;
        int index = 2;
        while (index < match.length){
            if (isEquals(match[index - 1], match[cn])){
                res[index++] = ++cn;
            }else if (cn > 0){
                cn = res[cn];
            }else {
                res[index++] = 0;
            }
        }
        return res;
    }

    private static List<String> preSerial(Node head) {
        List<String> res = new ArrayList<>();
        pre(head, res);
        return res;
    }

    private static void pre(Node head, List<String> res) {
        if (head == null) {
            res.add(null);
        } else {
            res.add(String.valueOf(head.value));
            pre(head.left, res);
            pre(head.right, res);
        }
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 10000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}
