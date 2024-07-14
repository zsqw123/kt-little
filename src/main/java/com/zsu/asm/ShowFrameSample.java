package com.zsu.asm;

public class ShowFrameSample {
    public void test(int a, Long b) {
        var c = a + 10;
        var s = "";
        if (a > b) {
            System.out.println(c);
        } else {
            long d1 = b - a;
            if (d1 > 0) {
                System.out.println(d1);
            } else {
                System.out.println(a);
            }
        }
        System.out.println();
    }
}
