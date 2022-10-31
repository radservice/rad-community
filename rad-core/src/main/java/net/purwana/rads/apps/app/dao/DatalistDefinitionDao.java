package net.purwana.rads.apps.app.dao;

import java.util.Collection;
import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.apps.app.model.DatalistDefinition;

public interface DatalistDefinitionDao extends AppVersionedObjectDao<DatalistDefinition> {

    public Collection<DatalistDefinition> getDatalistDefinitionList(String filterString, AppDefinition appDefinition, String sort, Boolean desc, Integer start, Integer rows);

    public Long getDatalistDefinitionListCount(String filterString, AppDefinition appDefinition);
}
