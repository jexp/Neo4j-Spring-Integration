package org.neo4j.spring.example.proteins;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Protein
{
    @Indexed( indexName = "proteins" )
    private String name;

    @RelatedTo( type = "BELONGS_TO_CLASS", elementClass = ProteinClass.class, direction = Direction.OUTGOING )
    private ProteinClass belongsToFamily;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
}
