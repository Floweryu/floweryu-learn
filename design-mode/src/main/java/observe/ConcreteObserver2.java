package observe;

/**
 * @author Floweryu
 * @date 2021/11/21 15:41
 */
public class ConcreteObserver2 implements Observer{

    @Override
    public void update() {
        System.out.println("观察者2收到信息，并进行处理");
    }
}
