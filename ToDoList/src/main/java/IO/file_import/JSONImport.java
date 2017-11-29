package IO.file_import;
/**
 * This class manages all code needed to import data to a json file.
 */

import IO.Import;
import IO.TemplateToDoList;
import IO.TemplateToDoState;
import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
/**
 * | Description |
 * This class manages all code needed to import data to a json file using gson
 * @author Kevin Smith
 * @version 1.0
 */
import Object.*;

public class JSONImport implements Import
{
    private TemplateToDoList toDoListTemplate = new TemplateToDoList();
    private TemplateToDoState toDoStateTemplate = new TemplateToDoState();

    /**
     * | Description |
     * This method initializes and preforms all steps needed to import data into a json file.
     * @return Boolean - representing the success state of the method
     */
    @Override
    public boolean importJSON()
    {
        Boolean toDoListImported = importFiles(TemplateToDoList.class, IOType.TO_DO_LIST, "ToDoList.json");
        Boolean toDoStateImported = importFiles(TemplateToDoState.class, IOType.TO_DO_STATE, "ToDoStates.json");

        if (toDoListImported && toDoStateImported)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * | Description |
     * This method returns all to do objects
     * @return ArrayList of to do objects
     */
    public ArrayList<ToDo> getToDoList()
    {
        return this.toDoListTemplate.getToDoList();
    }

    /**
     * | Description |
     * This method returns all to do checked state values
     * @return HashMap<UUID, Boolean> - representing to do id's and checked state true/false
     */
    public HashMap<UUID, Boolean> getToDoState() {
        return this.toDoStateTemplate.getToDoState();
    }

    private boolean importFiles(Class importClass, IOType templateType, String path)
    {
        FileReader reader = null;

        File file = new File(path);

        if (file.isFile())
        {
            try
            {
                reader = new FileReader(file);
                Gson gson = new Gson();

                if (templateType.equals(IOType.TO_DO_LIST))
                {
                    this.toDoListTemplate = gson.fromJson(reader, TemplateToDoList.class);

                }
                if (templateType.equals(IOType.TO_DO_STATE))
                {
                    this.toDoStateTemplate = gson.fromJson(reader, TemplateToDoState.class);
                }
                return true;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if(reader != null)
                {
                    try
                    {
                        reader.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
