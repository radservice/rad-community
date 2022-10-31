package net.purwana.rads.apps.app.dao;

import java.util.Collection;
import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.apps.app.model.UserviewDefinition;

public interface UserviewDefinitionDao extends AppVersionedObjectDao<UserviewDefinition> {

    public Collection<UserviewDefinition> getUserviewDefinitionList(String filterString, AppDefinition appDefinition, String sort, Boolean desc, Integer start, Integer rows);

    public Long getUserviewDefinitionListCount(String filterString, AppDefinition appDefinition);
}
