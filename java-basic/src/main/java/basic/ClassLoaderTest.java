package basic;

/**
 * @author Floweryu
 * @date 2024/3/22 11:26:01
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
//        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//        System.out.println(contextClassLoader);
//        System.out.println(contextClassLoader.getParent());
//        System.out.println(contextClassLoader.getParent().getParent());
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        classLoader.loadClass("Test2");
    }

}
