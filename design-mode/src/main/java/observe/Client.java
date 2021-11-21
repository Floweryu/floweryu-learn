package observe;

/**
 * @author Floweryu
 * @date 2021/11/21 15:42
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        subject.addObserver(new ConcreteObserver1());
        subject.addObserver(new ConcreteObserver2());
        subject.notifyObserver();
    }
}
