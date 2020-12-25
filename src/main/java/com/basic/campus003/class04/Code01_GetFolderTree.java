package com.basic.campus003.class04;

import java.util.TreeMap;

public class Code01_GetFolderTree {

    public static class Node {
        private String path;
        private TreeMap<String, Node> nextMap;

        public Node(String path) {
            this.path = path;
            this.nextMap = new TreeMap<>();
        }
    }

    public static void print(String[] paths){
        if (paths == null || paths.length == 0){
            return;
        }
        Node head = generateFolderName(paths);

        printProcess(head, 0);
    }

    public static Node generateFolderName(String[] foldPaths){
        Node head = new Node("");
        for (String str : foldPaths){
            String[] paths = str.split("\\\\");
            Node cur = head;
            for (String s : paths){
                if (!cur.nextMap.containsKey(s)){
                    cur.nextMap.put(s,new Node(s));
                }
                cur = cur.nextMap.get(s);
            }
        }
        return head;
    }

    public static void printProcess(Node node, int level){
        if (level != 0){
            System.out.println(getSpace(level)+node.path);
        }
        for (Node next : node.nextMap.values()){
            printProcess(next,level+1);
        }
    }

    private static String getSpace(int level) {
        String res = "";
        for (int i=1;i<level;i++){
            res += "  ";
        }
        return res;
    }

    public static void main(String[] args) {

        //    "a\b\c" '\'  a,b,c
        String test = "a\\b\\cd";





        //  "a\b\c"    "\"    a,b,c
        String[] arr = {"a\\c\\d","a\\c\\e","t\\c\\f"}; //    \\\\    \\   \
        print(arr);
    }
}
