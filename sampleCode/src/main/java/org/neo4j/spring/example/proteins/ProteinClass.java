package org.neo4j.spring.example.proteins;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Collections;
import java.util.Set;

@NodeEntity
public class ProteinClass
{

    @Indexed( indexName = "protein-classes" )
    private String name;

    @RelatedTo( type = "SUBCLASS_OF", elementClass = ProteinClass.class, direction = Direction.OUTGOING )
    private ProteinClass superClass;

    @RelatedTo( type = "SUBCLASS_OF", elementClass = ProteinClass.class, direction = Direction.INCOMING )
    private Set<ProteinClass> subclasses;

    @RelatedTo( type = "INTERACTS_WITH_CLASS", elementClass = ProteinClass.class, direction = Direction.BOTH )
    private Set<ProteinClass> interactions;

    @RelatedTo( type = "BELONGS_TO_CLASS", elementClass = Protein.class, direction = Direction.INCOMING )
    private Set<Protein> members;

    public ProteinClass( String name )
    {
        this.name = name;
    }
    
    public ProteinClass() {}

    public String getName()
    {
        return name;
    }

    public Set<Protein> getMembers()
    {
        return members;
    }

    public ProteinClass getSuperClass()
    {
        return superClass;
    }

    public void setSuperClass( ProteinClass superClass )
    {
        this.superClass = superClass;
    }

    public Set<ProteinClass> getSubclasses()
    {
        return subclasses;
    }

    public Set<ProteinClass> getInteractions()
    {
        return interactions;
    }
}
