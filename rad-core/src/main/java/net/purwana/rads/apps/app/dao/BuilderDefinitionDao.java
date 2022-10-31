package net.purwana.rads.apps.app.dao;

import java.util.Collection;
import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.apps.app.model.BuilderDefinition;

public interface BuilderDefinitionDao extends AppVersionedObjectDao<BuilderDefinition> {
    
    @Override
    public BuilderDefinition loadById(String id, AppDefinition appDefinition);

    @Override
    public boolean add(BuilderDefinition object);

    @Override
    public boolean update(BuilderDefinition object);
    
    public Collection<BuilderDefinition> getBuilderDefinitionList(String type, String filterString, AppDefinition appDefinition, String sort, Boolean desc, Integer start, Integer rows);

    public Long getBuilderDefinitionListCount(String type, String filterString, AppDefinition appDefinition);
    
}
