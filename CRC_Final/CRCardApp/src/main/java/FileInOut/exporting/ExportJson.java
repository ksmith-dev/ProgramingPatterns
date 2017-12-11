package FileInOut.exporting;

import FileInOut.ExportInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Model.ChangeRequestCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExportJson implements ExportInterface
{
    private ExportDataModel jsonExportModel;

    public ExportJson(HashMap<UUID, ChangeRequestCard> changeRequestCards)
    {
        this.jsonExportModel = new ExportDataModel(changeRequestCards);
    }

    /**
     * | Description |
     * This method writes data to file
     * @return Boolean - representing success state of method
     */
    @Override
    public boolean exportJson(String path)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(jsonExportModel);

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

    private class ExportDataModel
    {
        Map<UUID, ChangeRequestCard> changeRequestCards;

        public ExportDataModel(HashMap<UUID, ChangeRequestCard> changeRequestCards)
        {
            this.changeRequestCards = changeRequestCards;
        }

        public HashMap<UUID, ChangeRequestCard> getChangeRequestCards()
        {
            return (HashMap) this.changeRequestCards;
        }
    }
}
