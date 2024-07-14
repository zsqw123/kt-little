package com.zsu.asm;

public class Foo {
    void test(int a) {
        int b = 2;
        if (a > 0) {
            staticCall(a);
        } else {
            virtualCall(b);
        }
    }

    static void staticCall(int v) {
    }

    void virtualCall(int v) {
    }
}
