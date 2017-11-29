package Model;

import IO.file_export.JSONExport;
import IO.file_import.JSONImport;
import Object.*;
import View.GUI;

import java.util.*;

public class Model extends ToDoObservable
{
    private ArrayList<ToDo> toDoList = new ArrayList<>();
    private HashMap<UUID, Boolean> toDoStates = new HashMap<>();

    public Model(GUI view)
    {
        JSONImport jsonImport = new JSONImport();

        if(jsonImport.importJSON())
        {
            this.toDoList = jsonImport.getToDoList();
            this.toDoStates = jsonImport.getToDoState();
        }
        System.out.println("done importing");
        this.addToDoObserver(view);
    }

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

    public boolean updateToDoMessage(UUID id, String msg)
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

    public boolean updateToDoCheckedState(UUID id, Boolean checkedState)
    {
        return toDoStates.put(id, checkedState);
    }

    public boolean deleteToDo(UUID id)
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

    public ArrayList<ToDo> getToDoList()
    {
        return this.toDoList;
    }

    public void updateCheckedStates(UUID id, Boolean state)
    {
        toDoStates.replace(id, state);
        this.exportToJSON();
        notifyToDoObservers(ViewStates.LIST);
    }

    public HashMap<UUID, Boolean> getToDoStates()
    {
        return this.toDoStates;
    }

    private void exportToJSON()
    {
        JSONExport json = new JSONExport(toDoList, toDoStates);
        json.exportToJSON();
    }
}