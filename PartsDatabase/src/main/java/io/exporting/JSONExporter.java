package io.exporting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.IExporter;
import model.PartsDatabase;

import java.io.*;

public class JSONExporter implements IExporter
{
    private PartsDatabase partsDatabase;

    public JSONExporter(PartsDatabase partsDatabase)
    {
        this.partsDatabase = partsDatabase;
    }

    @Override
    public boolean exportParts()
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(partsDatabase);

        FileOutputStream fileOutputStream = null;

        File file = new File("PartsDatabase.json");
        try
        {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
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
