package io.exporting;

import io.IExporter;
import model.PartsDatabase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class JavaExporter implements IExporter
{
    private PartsDatabase partsDatabase;

    public JavaExporter(PartsDatabase partsDatabase) throws FileNotFoundException
    {
        this.partsDatabase = partsDatabase;
    }

    @Override
    public boolean exportParts()
    {
        try
        {
            FileOutputStream outputStream = new FileOutputStream("PartsDatabase.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outputStream);
            objectOutputFile.writeObject(partsDatabase);
            return true;
        }
        catch (IOException e)
        {
            System.out.println("IOException: " + e);
            return false;
        }
    }
}
