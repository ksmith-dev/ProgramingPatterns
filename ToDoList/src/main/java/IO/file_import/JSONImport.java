package IO.file_import;

import IO.Import;
import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import Object.*;

public class JSONImport implements Import
{
    private TemplateToDoList toDoListTemplate = new TemplateToDoList();
    private TemplateToDoState toDoStateTemplate = new TemplateToDoState();

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

    public ArrayList<ToDo> getToDoList()
    {
        return this.toDoListTemplate.getToDoList();
    }

    public HashMap<UUID, Boolean> getToDoState() {
        return this.toDoStateTemplate.getToDoState();
    }
}
