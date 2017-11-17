package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PartsDatabase implements Serializable
{
    private Collection<CarPart> parts;

    public PartsDatabase()
    {
        parts = new ArrayList<CarPart>();
    }

    public void addPart(CarPart part)
    {
        parts.add(part);
    }

    public Collection<CarPart> getParts()
    {
        return Collections.unmodifiableCollection(parts);
    }

    public void clear()
    {
        parts.clear();
    }
}
