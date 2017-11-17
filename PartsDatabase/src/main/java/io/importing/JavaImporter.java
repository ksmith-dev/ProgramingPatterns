package io.importing;

import io.IImporter;
import model.PartsDatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class JavaImporter implements IImporter
{
    private PartsDatabase partsDatabase;

    public JavaImporter() throws IOException
    {
    }

    @Override
    public boolean importParts()
    {
        try
        {
            FileInputStream inputStream = new FileInputStream("PartsDatabase.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            partsDatabase = (PartsDatabase) objectInputStream.readObject();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public PartsDatabase getPartsDatabase()
    {
        return partsDatabase;
    }
}
