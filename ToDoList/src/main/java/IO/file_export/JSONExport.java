package IO.file_export;

import IO.file_import.TemplateToDoList;
import IO.file_import.TemplateToDoState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Object.*;
import IO.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class JSONExport implements export
{
    private TemplateToDoList toDoList;
    private TemplateToDoState toDoStates;

    public JSONExport(ArrayList<ToDo> toDoList, HashMap<UUID, Boolean> toDoStates)
    {
        this.toDoList = new TemplateToDoList();
        this.toDoList.setToDoList(toDoList);
        this.toDoStates = new TemplateToDoState();
        this.toDoStates.setToDoStatus(toDoStates);
    }

    @Override
    public boolean exportToJSON()
    {
        if (exportFile(IOType.TO_DO_LIST,"ToDoList.json") && exportFile(IOType.TO_DO_STATE, "ToDoStates.json"))
        {
            System.out.println("both files were exported");
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean exportFile(IOType ioType, String path)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = "";

        if(ioType.equals(IOType.TO_DO_LIST))
        {
            json = gson.toJson(toDoList);
        }
        if(ioType.equals(IOType.TO_DO_STATE))
        {
            json = gson.toJson(toDoStates);
        }

        FileOutputStream fileOutputStream = null;

        File file = new File(path);
        try
        {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(json.getBytes());
            fileOutputStream.close();
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(fileOutputStream != null)
            {
                try
                {
                    fileOutputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
