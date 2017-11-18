package io.importing;
/**
 * This class is used to import PartsDatabase object from a java serialized object file format.
 */
import io.IImporter;
import model.PartsDatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * | Description |
 * This class is used to import PartsDatabase object from a java serialized object file format.
 * @author Kevin Smith
 * @version 1.0
 */
public class JavaImporter implements IImporter
{
    private PartsDatabase partsDatabase;

    public JavaImporter() throws IOException
    {
    }

    /**
     * This method imports a PartsDatabase object from a java serialized object file format.
     * @return boolean - representing true for a successful transfer or false for an unsuccessful transfer
     */
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

    /**
     * This method retrieves the PartsDatabase object;
     * @return PartsDatabase object, note this can be null if not properly imported
     */
    public PartsDatabase getPartsDatabase()
    {
        return partsDatabase;
    }
}
