package Object;
/**
 * This class handles observable functionality
 */

import java.util.HashSet;
import java.util.Set;

/**
 * | Description |
 * This class handles observable functionality, such as notifyObservers and add/remove observers.
 * @author Kevin Smith
 * @version 1.0
 */
public class ToDoObservable
{
    private Set<ToDoObserver> observers;

    /**
     * | Constructor |
     * Instantiates a new HashSet to store observer class objects.
     */
    public ToDoObservable()
    {
        observers = new HashSet<>();
    }

    /**
     * | Description |
     * This method loops through observer class objects and calls their update method and sends pertinent data via
     * Object class objects of varying amounts with variable length parameter usage.
     * @param args
     */
    public void notifyToDoObservers(Object... args)
    {
        for (ToDoObserver observer : observers)
        {
            observer.updateObservable(this, args);
        }
    }

    /**
     * | Description |
     * This method adds observer class object to a hash set for management and usage.
     * @param observer
     */
    public void addToDoObserver(ToDoObserver observer)
    {
        this.observers.add(observer);
    }

    /**
     * | Description |
     * This method removes observer class objects, to break observer / observable relationship
     * @param observer
     */
    public void removeToDoObserver(ToDoObserver observer)
    {
        this.observers.remove(observer);
    }
}
