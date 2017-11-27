package Model;

import java.util.ArrayList;
import java.util.List;

public class ToDoList
{
    private List<ToDo> toDoList;

    public ToDoList(List<ToDo> toDoList)
    {
        this.toDoList = toDoList;
    }

    public ArrayList<ToDo> getToDoList()
    {
        return (ArrayList<ToDo>) toDoList;
    }
}
