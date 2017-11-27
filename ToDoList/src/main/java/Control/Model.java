package Control;

import IO.file_export.JSONExport;
import IO.file_import.JSONImport;
import Model.*;

import java.util.ArrayList;
import java.util.UUID;

public class Model implements ToDoObserver
{
    private ArrayList<ToDo> toDoList;

    public Model()
    {
        JSONImport json = new JSONImport();
        this.toDoList = (ArrayList) json.getToDoList();
    }

    public void addToDo(String msg)
    {
        ToDo toDo = new ToDo(msg);
        this.toDoList.add(toDo);
    }

    public ToDo getToDoByUUID(UUID id)
    {
        for (ToDo toDo : toDoList)
        {
            if(toDo.getId().equals(id))
            {
                return toDo;
            }
        }
        return null;
    }

    public boolean updateToDo(UUID id, String msg)
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

    public boolean deleteToDo(UUID id)
    {
        for (ToDo toDo : toDoList)
        {
            return toDoList.remove(toDo);
        }
        return false;
    }

    public boolean searchToDeleteToDo(String id)
    {
        for (ToDo toDo : toDoList)
        {
            if (toDo.getId().toString().equals(id))
            {
                return this.toDoList.remove(toDo);
            }
        }
        return false;
    }

    public boolean searchToUpdateToDo(String id, String msg)
    {
        for (ToDo toDo : toDoList)
        {
            if (toDo.getId().toString().equals(id))
            {
                toDo.setMsg(msg);
                return true;
            }
        }
        return false;
    }

    public ArrayList<ToDo> getToDoList()
    {
        return toDoList;
    }

    private void exportToJSON()
    {
        JSONExport json = new JSONExport(toDoList);
    }

    @Override
    public void update(ToDoObservable observable, Object... args)
    {
        
    }
}
