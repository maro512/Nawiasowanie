package com.company;

import java.util.Scanner;

class Nawiasowanie {
    private int p[];
    private int pre[][];
    private int k = 0;
    private int size;

    public Nawiasowanie(int n) {
        p = new int[n + 1];
        pre = new int[n][n];
        size = n;
    }

    public void addElem(int p) {
        this.p[k++] = p;
    }

    public int minCost() {
        int C[][] = new int[size][size];
        int j;

        for (int i = 0; i < size - 1; i++) C[i][i + 1] = p[i] * p[i + 1] * p[i + 2];

        for (int l = 3; l <= size; l++) {
            for (int i = 0; i <= size - l; i++) {
                j = i + l - 1;
                C[i][j] = Integer.MAX_VALUE;
                int c;

                for (int m = i + 1; m <= j; m++) {
                    c = (m - 1 <= i ? 0 : C[i][m - 1]) + (m >= j ? 0 : C[m][j]) + p[i] * p[m] * p[j + 1];
                    if (c < C[i][j]) {
                        C[i][j] = c;
                        pre[i][j] = m;
                    }
                }
            }
        }
        return C[0][size - 1];
    }

    public String nawiasy(int i, int j) {
        if (i + 1 < j) {
            String s1 = nawiasy(i, pre[i][j] - 1);
            if (s1.length() > 2) s1 = "(" + s1 + ")";
            String s2 = nawiasy(pre[i][j], j);
            if (s2.length() > 2) s2 = "(" + s2 + ")";
            return s1 + s2;
        } else if (i < j) return "A" + (i + 1) + "A" + (j + 1);
        return "A" + (i + 1);
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Nawiasowanie t = new Nawiasowanie(7);
        t.addElem(22);
        t.addElem(30);
        t.addElem(15);
        t.addElem(12);
        t.addElem(28);
        t.addElem(18);
        t.addElem(25);
        t.addElem(20);
        System.out.println(t.minCost());
        System.out.println(t.nawiasy(0, 6));
    }
}
