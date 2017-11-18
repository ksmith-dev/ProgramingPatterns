package io.exporting;
/**
 * This class is used to export PartsDatabase object into a gson file format.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.IExporter;
import model.PartsDatabase;

import java.io.*;

/**
 * | Description |
 * This class is used to export PartsDatabase object into a gson file format.
 * @author Kevin Smith
 * @version 1.0
 */
public class JSONExporter implements IExporter
{
    private PartsDatabase partsDatabase;

    public JSONExporter(PartsDatabase partsDatabase)
    {
        this.partsDatabase = partsDatabase;
    }

    /**
     * This method exports a PartsDatabase object into a gson file format.
     * @return boolean - representing true for a successful transfer or false for an unsuccessful transfer
     */
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
            fileOutputStream.close();
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
