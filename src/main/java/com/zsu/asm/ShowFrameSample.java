package com.zsu.asm;

public class ShowFrameSample {
    public void test(int a, Long b) {
        var c = a + 10;
        var s = "";
        if (a > b) {
            System.out.println(c);
        } else {
            long d1 = b - a;
            int w = d1 > 0 ? 1 : 2;
            if (d1 > 0) {
                System.out.println(d1);
            } else {
                System.out.println(a);
            }
        }
        System.out.println();
    }

    private void xxx(@Simple Integer a) {
        try {
            @Simple Integer c = 1;
            System.out.println(1);
        } catch (@Simple Exception e) {
            System.out.println(2);
        }
    }
}
