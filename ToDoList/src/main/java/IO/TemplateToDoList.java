package IO;
/**
 * This class serves as a template for gson to structure its data.
 */

import java.util.ArrayList;
import Object.ToDo;

/**
 * | Description |
 * This class serves as a template for gson to structure its data.
 * @author Kevin Smith
 * @version 1.0
 */
public class TemplateToDoList
{
    private ArrayList<ToDo> toDoList;

    /**
     * | Constructor |
     * This initializes a new ArrayList for this class
     */
    public TemplateToDoList()
    {
        this.toDoList = new ArrayList<ToDo>();
    }

    /**
     * | Description |
     * This method returns a list of to do objects
     * @return ArrayList of to do objects
     */
    public ArrayList<ToDo> getToDoList()
    {
        return this.toDoList;
    }

    /**
     * | Description |
     * This method sets this classes to do list
     * @param toDoList ArrayList of to do objects
     */
    public void setToDoList(ArrayList<ToDo> toDoList)
    {
        this.toDoList = toDoList;
    }
}
