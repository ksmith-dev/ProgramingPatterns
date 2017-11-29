package IO.file_import;

import java.util.ArrayList;
import Object.ToDo;

public class TemplateToDoList
{
    private ArrayList<ToDo> toDoList;

    public TemplateToDoList()
    {
        this.toDoList = new ArrayList<ToDo>();
    }

    public ArrayList<ToDo> getToDoList()
    {
        return this.toDoList;
    }

    public void setToDoList(ArrayList<ToDo> toDoList)
    {
        this.toDoList = toDoList;
    }
}
