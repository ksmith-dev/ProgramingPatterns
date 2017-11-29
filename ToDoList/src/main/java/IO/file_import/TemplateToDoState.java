package IO.file_import;

import java.util.HashMap;
import java.util.UUID;

public class TemplateToDoState
{
    private HashMap<UUID, Boolean> toDoStates;

    public TemplateToDoState()
    {
        this.toDoStates = new HashMap<>();
    }

    public HashMap<UUID, Boolean> getToDoState()
    {
        return toDoStates;
    }

    public void setToDoStatus(HashMap<UUID, Boolean> toDoStates)
    {
        this.toDoStates = toDoStates;
    }
}