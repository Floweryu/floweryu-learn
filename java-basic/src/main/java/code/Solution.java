package code;

public class Solution {
    Object object = new Object();
    public void method1() {
        synchronized (object) {
            System.out.println("sss");
        }
        method2();
    }

    private static void method2() {

    }

}
