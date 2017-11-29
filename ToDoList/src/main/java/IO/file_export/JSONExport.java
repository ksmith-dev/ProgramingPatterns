package IO.file_export;
/**
 * This class manages all code needed to export data to a json file.
 */

import IO.TemplateToDoList;
import IO.TemplateToDoState;
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

/**
 * | Description |
 * This class manages all code needed to import data to a json file using gson
 * @author Kevin Smith
 * @version 1.0
 */
public class JSONExport implements export
{
    private TemplateToDoList toDoList;
    private TemplateToDoState toDoStates;

    /**
     * | Description |
     * This method initializes and preforms all steps needed to export data into a json file.
     * @param toDoList ArrayList - representing to do class level objects
     * @param toDoStates HashMap<UUID, Boolean> - representing to do id's and checked states true/false
     */
    public JSONExport(ArrayList<ToDo> toDoList, HashMap<UUID, Boolean> toDoStates)
    {
        this.toDoList = new TemplateToDoList();
        this.toDoList.setToDoList(toDoList);
        this.toDoStates = new TemplateToDoState();
        this.toDoStates.setToDoStatus(toDoStates);
    }

    /**
     * | Description |
     * This method writes data to file
     * @return Boolean - representing success state of method
     */
    @Override
    public boolean exportToJSON()
    {
        if (exportFile(IOType.TO_DO_LIST,"ToDoList.json") && exportFile(IOType.TO_DO_STATE, "ToDoStates.json"))
        {
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
