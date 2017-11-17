package io.importing;

import io.IImporter;
import model.CarPart;
import model.PartsDatabase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class XMLImporter implements IImporter
{
    private PartsDatabase partsDatabase = new PartsDatabase();

    public XMLImporter()
    {
    }

    @Override
    public boolean importParts()
    {
        try
        {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("PartsDatabase.xml");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            NodeList parts = document.getElementsByTagName("part");

            for(int i=0; i<parts.getLength(); i++)
            {
                CarPart carPart = new CarPart();

                Element xml = (Element) parts.item(i);

                carPart.setId(xml.getElementsByTagName("id").item(0).getTextContent());
                carPart.setManufacturer(xml.getElementsByTagName("manufacturer").item(0).getTextContent());
                carPart.setListPrice(Double.parseDouble(xml.getElementsByTagName("list-price").item(0).getTextContent()));

                NodeList categories = xml.getElementsByTagName("categories").item(0).getChildNodes();

                String[] array = new String[categories.getLength()];

                for(int j=0; j<categories.getLength(); j++)
                {
                    Element category = (Element) categories.item(j);
                    array[j] = category.getElementsByTagName("category").item(j).getTextContent();
                }

                carPart.setCategories(array);

                partsDatabase.addPart(carPart);
            }
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public PartsDatabase getPartsDatabase()
    {
        return this.partsDatabase;
    }
}
