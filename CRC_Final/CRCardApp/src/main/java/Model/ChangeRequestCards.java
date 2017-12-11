package Model;

import java.util.*;

public class ChangeRequestCards
{
    private Map<UUID, ChangeRequestCard> changeRequestCards;

    public ChangeRequestCards()
    {
        changeRequestCards = new HashMap<>();
    }

    public ChangeRequestCard getChangeRequestCardById(UUID uuid)
    {
        return changeRequestCards.get(uuid);
    }

    public void addChangeRequestCard(ChangeRequestCard changeRequestCard)
    {
        changeRequestCards.put(changeRequestCard.getId(), changeRequestCard);
    }
}
