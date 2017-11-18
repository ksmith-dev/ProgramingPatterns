package io.exporting;
/**
 * This class is used to export PartsDatabase object into a java serialized object file format.
 */
import io.IExporter;
import model.PartsDatabase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
/**
 * | Description |
 * This class is used to export PartsDatabase object into a java serialized object file format.
 * @author Kevin Smith
 * @version 1.0
 */
public class JavaExporter implements IExporter
{
    private PartsDatabase partsDatabase;

    public JavaExporter(PartsDatabase partsDatabase) throws FileNotFoundException
    {
        this.partsDatabase = partsDatabase;
    }
    /**
     * This method exports a PartsDatabase object into a java serialized object file format.
     * @return boolean - representing true for a successful transfer or false for an unsuccessful transfer
     */
    @Override
    public boolean exportParts()
    {
        try
        {
            FileOutputStream outputStream = new FileOutputStream("PartsDatabase.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outputStream);
            objectOutputFile.writeObject(partsDatabase);
            outputStream.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println("IOException: " + e);
            return false;
        }
    }
}
