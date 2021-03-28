package com.ocean.baseframework.jvm;

/**
 * <clinit> 方法只有在类中有静态变量或静态代码块时生成并执行
 */
public class ClinitTest {
    static {
        after = 1;
        // 静态变量要先声明后才能使用，但是可以赋值
//        System.out.println(after);
    }
    static int after;
    static {
        System.out.println(after);
    }

    public static void main(String[] args) {
        System.out.println("主函数：" + after);
    }
}
