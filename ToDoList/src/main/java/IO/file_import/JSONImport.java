package IO.file_import;

import IO.Import;

import Model.*;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JSONImport implements Import
{
    private List<ToDo> toDoList;

    @Override
    public boolean importList()
    {
        FileReader reader = null;

        File file = new File("ToDoList.json");

        try
        {
            reader = new FileReader(file);
            Gson gson = new Gson();

            ToDoList jsonToDoList = gson.fromJson(reader, ToDoList.class);
            List<ToDo> toDoList = jsonToDoList.getToDoList();

            for (ToDo toDo : toDoList)
            {
                this.toDoList.add(toDo);
            }

        } catch (FileNotFoundException e)
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
        return false;
    }

    public List<ToDo> getToDoList()
    {
        return toDoList;
    }
}
