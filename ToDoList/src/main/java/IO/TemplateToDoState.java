package IO;
/**
 * | Description |
 * This is a template class for gson to structure its data to match project needs
 */

import java.util.HashMap;
import java.util.UUID;

/**
 * | Description |
 * This is a template class for gson to structure its data to match project needs
 * @author Kevin Smith
 * @version 1.0
 */
public class TemplateToDoState
{
    private HashMap<UUID, Boolean> toDoStates;

    /**
     * | Constructor |
     *  Initializes new HashMap instance
     */
    public TemplateToDoState()
    {
        this.toDoStates = new HashMap<>();
    }

    /**
     * | Description |
     * This returns the to do state data in the form of a HashMap<UUID, Boolean> format
     * @return
     */
    public HashMap<UUID, Boolean> getToDoState()
    {
        return toDoStates;
    }

    /**
     * | Description |
     * This method allows JSONExport class to set to do status data for export to file.
     * @param toDoStates
     */
    public void setToDoStatus(HashMap<UUID, Boolean> toDoStates)
    {
        this.toDoStates = toDoStates;
    }
}