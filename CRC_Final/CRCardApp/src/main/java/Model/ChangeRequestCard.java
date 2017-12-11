package Model;

import java.util.*;

public class ChangeRequestCard
{
    private UUID id = UUID.randomUUID();
    private Date requestDate = new Date();
    private String requesterName = "";
    private RequestStates state;
    private HashMap<RequestStates, Date> statesAndDates = new HashMap<>();
    private RequestPriority priority;
    private String description;
    private String impacted;
    private int estimatedEffort;
    private String inclusionId;
    private String lastEditedById = "";

    public void setId(UUID id)
    {
        this.id = id;
    }

    public void setRequesterName(String requesterName)
    {
        this.requesterName = requesterName;
    }

    public String getLastEditedById()
    {
        return lastEditedById;
    }

    public void setLastEditedById(String lastEditedById)
    {
        this.lastEditedById = lastEditedById;
    }

    public UUID getId()
    {
        return id;
    }

    public void setRequestDate(Date date)
    {
        this.requestDate = date;
    }

    public Date getRequestDate()
    {
        return requestDate;
    }

    public String getRequesterName()
    {
        return requesterName;
    }

    public RequestStates getState()
    {
        if (!statesAndDates.isEmpty())
        {
            Date max;

            Map.Entry<RequestStates, Date> previousEntry = null;

            for (Map.Entry<RequestStates, Date> entry : statesAndDates.entrySet())
            {
                if (previousEntry != null)
                {
                    if(entry.getValue().compareTo(previousEntry.getValue()) > 0)
                    {
                        this.state = entry.getKey();
                        previousEntry = entry;
                    }
                    else
                    {
                        this.state = previousEntry.getKey();
                    }
                }
                else
                {
                    state = entry.getKey();
                    previousEntry = entry;
                }
            }
        }
        return state;
    }

    public void setState(RequestStates state)
    {
        this.state = state;
    }

    public Date getDate(RequestStates state)
    {
        return statesAndDates.get(state);
    }

    public void addDate(RequestStates state, Date date)
    {
        this.statesAndDates.put(state, date);
    }

    public RequestPriority getPriority()
    {
        return priority;
    }

    public void setPriority(RequestPriority priority)
    {
        this.priority = priority;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImpacted()
    {
        return impacted;
    }

    public void setImpacted(String impacted)
    {
        this.impacted = impacted;
    }

    public int getEstimatedEffort()
    {
        return estimatedEffort;
    }

    public void setEstimatedEffort(int estimatedEffort)
    {
        this.estimatedEffort = estimatedEffort;
    }

    public String getInclusionId()
    {
        return inclusionId;
    }

    public void setInclusionId(String inclusionId)
    {
        this.inclusionId = inclusionId;
    }
}
