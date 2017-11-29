package Control;
/**
 * | Description |
 * This class moderates functionality between the view and model classes of this application.
 */

import Model.Model;
import Object.*;
import View.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * | Description |
 * This class moderates functionality between the view and model classes of this application.
 * @author Kevin Smith
 * @version 1.0
 */
public class ToDoControl
{
    private Model model;

    /**
     * | Constructor |
     * This creates an instance of the model class level object
     * @param view GUI - representing the view class level object.
     */
    public ToDoControl(GUI view)
    {
        this.model = new Model(view);
    }

    /**
     * | Description |
     * This method adds a new to do object to the model
     * @param msg String - representing the to do message
     */
    public void addNewToDoToModel(String msg)
    {
        this.model.addToDo(msg);
    }

    /**
     * | Description |
     * This method retrieves the current to do list from the model
     * @return ArrayList - representing a list of to do class level objects
     */
    public ArrayList<ToDo> getToDoListFromModel()
    {
        return this.model.getToDoList();
    }

    /**
     * | Description |
     * This method retrieves the current list to do states from the model
     * @return ArrayList - representing a list of to do id's and checked states true/false
     */
    public HashMap<UUID, Boolean> getToDoStatesFromModel()
    {
        return this.model.getToDoStates();
    }

    /**
     * | Description |
     * This method updates the to do checked state in the model
     * @param id UUID - representing the to do id
     * @param state Boolean - representing the checked state of the to do true/false
     */
    public void updateCheckedStatesOfModel(UUID id, Boolean state)
    {
        this.model.updateCheckedStates(id, state);
    }
}
