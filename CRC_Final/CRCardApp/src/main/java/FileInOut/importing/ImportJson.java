package FileInOut.importing;

import FileInOut.ImportInterface;
import com.google.gson.Gson;
import Model.ChangeRequestCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ImportJson implements ImportInterface
{
    private ImportDataModel changeRequestCards;

    /**
     * | Description |
     * This method initializes and preforms all steps needed to import data into a json file.
     * @return Boolean - representing the success state of the method
     */
    @Override
    public boolean importJson(String path)
    {
        FileReader reader = null;

        File file = new File(path);

        if (file.isFile())
        {
            try
            {
                reader = new FileReader(file);
                Gson gson = new Gson();

                changeRequestCards = gson.fromJson(reader, ImportDataModel.class);

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

    public HashMap<UUID, ChangeRequestCard> getChangeRequestCards()
    {
        return changeRequestCards.getChangeRequestCards();
    }

    private class ImportDataModel
    {
        Map<UUID, ChangeRequestCard> changeRequestCards;

        public ImportDataModel(HashMap<UUID, ChangeRequestCard> changeRequestCards)
        {
            this.changeRequestCards = changeRequestCards;
        }

        public HashMap<UUID, ChangeRequestCard> getChangeRequestCards()
        {
            return (HashMap) this.changeRequestCards;
        }
    }
}
