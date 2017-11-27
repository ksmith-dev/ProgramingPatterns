package IO.file_export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Model.*;
import IO.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JSONExport implements export
{
    private List<ToDo> toDoList;

    public JSONExport(List<ToDo> toDoList)
    {
        this.toDoList = toDoList;
    }

    @Override
    public boolean exportToDoList()
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(toDoList);

        FileOutputStream fileOutputStream = null;

        File file = new File("PartsDatabase.json");
        try
        {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jsonString.getBytes());
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
