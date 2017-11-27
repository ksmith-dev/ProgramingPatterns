package Model;

import java.util.UUID;

public class ToDo extends ToDoObservable
{
    private UUID id = UUID.randomUUID();
    private String msg;

    public ToDo(String msg)
    {
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public UUID getId()
    {
        return id;
    }
}
