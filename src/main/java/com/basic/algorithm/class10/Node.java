package com.basic.algorithm.class10;

import java.util.ArrayList;
import java.util.List;

public class Node {

    int value;
    int in;
    int out;
    List<Node> nexts;
    List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
