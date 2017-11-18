package io.exporting;
/**
 * This class is used to export PartsDatabase object into a xml file format.
 */

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import io.IExporter;
import model.CarPart;
import model.PartsDatabase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * | Description |
 * This class is used to export PartsDatabase object into a xml file format.
 * @author Kevin Smith
 * @version 1.0
 */
public class XMLExporter implements IExporter
{
    private PartsDatabase partsDatabase;

    public XMLExporter(PartsDatabase partsDatabase)
    {
        this.partsDatabase = partsDatabase;
    }

    /**
     * | Description |
     * This method exports the PartsDatabase object into an xml file format
     * @return boolean - representing true for a successful transfer or false for an unsuccessful transfer
     */
    @Override
    public boolean exportParts()
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document xml = documentBuilder.newDocument();

            Element root = xml.createElement("parts");

            for (CarPart part : partsDatabase.getParts())
            {
                Element main = xml.createElement("part");

                Element id = xml.createElement("id");
                Text text = xml.createTextNode(part.getId());
                id.appendChild(text);

                main.appendChild(id);

                Element manufacturer = xml.createElement("manufacturer");
                text = xml.createTextNode(part.getManufacturer());
                manufacturer.appendChild(text);

                main.appendChild(manufacturer);

                Element listPrice = xml.createElement("list-price");
                text = xml.createTextNode(Double.toString(part.getListPrice()));
                listPrice.appendChild(text);


                main.appendChild(listPrice);

                Element categories = xml.createElement("categories");

                for(String category : part.getCategories())
                {
                    Element categoryElement = xml.createElement("category");
                    text = xml.createTextNode(category);
                    categoryElement.appendChild(text);

                    categories.appendChild(categoryElement);
                }

                main.appendChild(categories);

                root.appendChild(main);
            }
            xml.appendChild(root);

            OutputFormat format = new OutputFormat(xml);
            format.setIndenting(true);

            File file = new File("PartsDatabase.xml");
            FileOutputStream outputStream = new FileOutputStream(file);

            XMLSerializer serializer = new XMLSerializer(outputStream, format);

            serializer.serialize(xml);

            outputStream.close();

            return true;
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
