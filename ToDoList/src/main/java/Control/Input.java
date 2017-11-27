package Control;

import View.GUI;
import Model.*;

public class Input implements ToDoObserver
{
    private Model appModel = new Model();
    private GUI view = new GUI();

    public void addToDo(String msg)
    {
        appModel.addToDo(msg);
    }

    @Override
    public void update(ToDoObservable observable, Object... args)
    {
        if (observable instanceof ToDo)
        {
            String[] arguments = (String[]) args;

            ToDo toDo = (ToDo)observable;
            //TODO - this may not work
            toDo.setMsg(arguments[arguments.length-1]);
        }
    }
}
