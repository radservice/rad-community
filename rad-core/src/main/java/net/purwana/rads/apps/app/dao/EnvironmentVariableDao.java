package net.purwana.rads.apps.app.dao;

import java.util.Collection;
import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.apps.app.model.EnvironmentVariable;

public interface EnvironmentVariableDao extends AppVersionedObjectDao<EnvironmentVariable> {

    public Collection<EnvironmentVariable> getEnvironmentVariableList(String filterString, AppDefinition appDefinition, String sort, Boolean desc, Integer start, Integer rows);

    public Long getEnvironmentVariableListCount(String filterString, AppDefinition appDefinition);
    
    public Integer getIncreasedCounter(String id, String remark, AppDefinition appDef);
}
