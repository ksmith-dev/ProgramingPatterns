package Object;

import java.util.HashSet;
import java.util.Set;

public class ToDoObservable
{
    private Set<ToDoObserver> observers;

    public ToDoObservable()
    {
        observers = new HashSet<>();
    }

    public void notifyToDoObservers(Object... args)
    {
        for (ToDoObserver observer : observers)
        {
            observer.updateObservable(this, args);
        }
    }

    public void addToDoObserver(ToDoObserver observer)
    {
        this.observers.add(observer);
    }

    public void removeToDoObserver(ToDoObserver observer)
    {
        this.observers.remove(observer);
    }
}
