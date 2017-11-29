package Control;

import Model.Model;
import Object.*;
import View.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ToDoControl
{
    private Model model;

    public ToDoControl(GUI view)
    {
        this.model = new Model(view);
    }

    public void addNewToDoToModel(String msg)
    {
        this.model.addToDo(msg);
    }

    public ArrayList<ToDo> getToDoListFromModel()
    {
        return this.model.getToDoList();
    }

    public HashMap<UUID, Boolean> getToDoStatesFromModel()
    {
        return this.model.getToDoStates();
    }

    public void updateCheckedStatesOfModel(UUID id, Boolean state)
    {
        this.model.updateCheckedStates(id, state);
    }
}
