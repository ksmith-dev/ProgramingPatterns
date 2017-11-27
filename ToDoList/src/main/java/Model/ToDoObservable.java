package Model;

import java.util.HashSet;
import java.util.Set;

public class ToDoObservable
{
    private Set<ToDoObserver> observers;

    public ToDoObservable()
    {
        observers = new HashSet<>();
    }

    public void notify(Object... args)
    {
        for (ToDoObserver observer : observers)
        {
            observer.update(this, args);
        }
    }

    public void add(ToDoObserver observer)
    {
        this.observers.add(observer);
    }

    public void remove(ToDoObserver observer)
    {
        observers.remove(observer);
    }
}
