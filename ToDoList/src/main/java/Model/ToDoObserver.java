package Model;

public interface ToDoObserver
{
    public void update(ToDoObservable observable, Object... args);
}
