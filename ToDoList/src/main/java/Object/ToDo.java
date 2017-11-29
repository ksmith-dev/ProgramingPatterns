package Object;
/**
 * This class is object level class, storing data such as id and message.
 */

import java.util.UUID;

/**
 * | Description |
 * This class is plain java object level class, storing data such as id and message.
 * @author Kevin Smith
 * @version 1.0
 */
public class ToDo
{
    private UUID id = UUID.randomUUID();
    private String msg;

    /**
     * | Constructor |
     * Instantiates a new to do class object with String message passed into method call
     * @param msg String - representing to do message
     */
    public ToDo(String msg)
    {
        this.msg = msg;
    }

    /**
     * | Description |
     * This method gets a to do message
     * @return String - representing a to do message
     */
    public String getMsg()
    {
        return msg;
    }

    /**
     * | Description |
     * This method sets a to do message
     * @param msg String - representing to do message
     */
    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    /**
     * | Description |
     * This method gets UUID class level object
     * @return UUID - representing the to do id
     */
    public UUID getId()
    {
        return id;
    }
}
