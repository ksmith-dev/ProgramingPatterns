package io.importing;

import com.google.gson.Gson;
import io.IImporter;
import model.CarPart;
import model.PartsDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JSONImporter implements IImporter
{
    private PartsDatabase partsDatabase;

    public JSONImporter()
    {
    }

    @Override
    public boolean importParts()
    {
        FileReader reader = null;

        File file = new File("PartsDatabase.json");

        try
        {
            reader = new FileReader(file);
            Gson gson = new Gson();

            Parts carParts = gson.fromJson(reader, Parts.class);

            List<CarPart> parts = carParts.getCarParts();

            for (CarPart part : parts)
            {
                partsDatabase.addPart(part);
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
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    static class Parts
    {
        List<CarPart> carParts;

        public List<CarPart> getCarParts()
        {
            return carParts;
        }

        public void setCarParts(List<CarPart> carParts)
        {
            this.carParts = carParts;
        }
    }

    public PartsDatabase getPartsDatabase()
    {
        return partsDatabase;
    }
}
