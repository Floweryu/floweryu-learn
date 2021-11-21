package observe;

import java.util.Vector;

/**
 * @author Floweryu
 * @date 2021/11/21 15:32
 */
abstract class Subject {
    private Vector<Observer> obs = new Vector<>();
    
    public void addObserver(Observer ob) {
        this.obs.add(ob);
    }
    
    public void delObserver(Observer ob) {
        this.obs.remove(ob);
    }
    
    protected void notifyObserver() {
        for (Observer ob : obs) {
            ob.update();
        }
    }

    /**
     * 被观察者动作
     */
    public abstract void doSomething();
}
