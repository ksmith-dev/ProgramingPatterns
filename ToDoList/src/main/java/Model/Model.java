package Model;
/**
 * | Description |
 * This class manages all aspect of management for the model of the application, and stores all instances of to do objects.
 * Also this class manages a separate set of to do state instance data for that is also managed by this class.
 */

import IO.file_export.JSONExport;
import IO.file_import.JSONImport;
import Object.*;
import View.GUI;

import java.util.*;

/**
 * | Description |
 * This class manages all aspect of management for the model of the application, and stores all instances of to do objects.
 * Also this class manages a separate set of to do state instance data for that is also managed by this class.
 * @author Kevin Smith
 * @version 1.0
 */
public class Model extends ToDoObservable
{
    private ArrayList<ToDo> toDoList = new ArrayList<>();
    private HashMap<UUID, Boolean> toDoStates = new HashMap<>();

    /**
     * | Constructor |
     * Initializes JSON import and assigns input to correct model fields for storage.
     * @param view Requires GUI class object, that handles view code operations.
     */
    public Model(GUI view)
    {
        JSONImport jsonImport = new JSONImport();

        if(jsonImport.importJSON())
        {
            this.toDoList = jsonImport.getToDoList();
            this.toDoStates = jsonImport.getToDoState();
        }
        this.addToDoObserver(view);
    }

    /**
     * | Description |
     * This method creates a new to do object and assigns the String parameter to the message field
     * @param msg Requires a String - representing the to do message
     */
    public void addToDo(String msg)
    {
        ToDo toDo = new ToDo(msg);
        this.toDoList.add(toDo);
        this.toDoStates.put(toDo.getId(), false);
        //save to file
        this.exportToJSON();
        //notify all observers
        notifyToDoObservers(ViewStates.LIST);
    }

    /**
     * | Description |
     * This method returns a list of to do class level objects
     * @return ArrayList of to do objects - representing current list of to do objects.
     */
    public ArrayList<ToDo> getToDoList()
    {
        return this.toDoList;
    }

    /**
     * | Description |
     * This method updates the checked state for a given to do state instance
     * @param id UUID - representing the to do object id
     * @param state Boolean - representing the current checked state of given to do according to its id
     */
    public void updateCheckedStates(UUID id, Boolean state)
    {
        toDoStates.replace(id, state);
        this.exportToJSON();
        notifyToDoObservers(ViewStates.LIST);
    }

    /**
     * | Description |
     * This method gets a current HashMap of the checked states of to do objects
     * @return HashMap<UUID, Boolean> - representing current hash map of checked states for to do objects
     */
    public HashMap<UUID, Boolean> getToDoStates()
    {
        return this.toDoStates;
    }

    private void exportToJSON()
    {
        JSONExport json = new JSONExport(toDoList, toDoStates);
        json.exportToJSON();
    }

    /**
     * | Description |
     * This method updates an existing to do instance by locating the instance by its UUID and once found changes
     * its message to the String parameter passed into the method.
     * @param id Requires an UUID object - representing the to do instance id
     * @param msg Requires a String object - representing the new to do message
     * @return Boolean - representing the success status of the update method its self
     */
    private boolean updateToDoMessage(UUID id, String msg)
    {
        for (ToDo toDo : toDoList)
        {
            if (toDo.getId().equals(id))
            {
                toDo.setMsg(msg);
                return true;
            }
        }
        return false;
    }

    private boolean deleteToDo(UUID id)
    {
        Boolean deleteToDo = false;
        Boolean deleteToDoState = false;

        for (ToDo toDo : toDoList)
        {
            if (toDo.getId().equals(id))
            {
                deleteToDo = toDoList.remove(toDo);
                deleteToDoState = toDoStates.remove(toDo.getId());
            }
        }
        if (deleteToDo && deleteToDoState)
        {
            return true;
        }
        else
        {
            System.out.println("something did not get deleted line 75 in code");
            return false;
        }
    }
}