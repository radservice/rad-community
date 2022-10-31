package net.purwana.rads.apps.app.dao;

import java.util.Collection;
import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.apps.app.model.AppResource;

public interface AppResourceDao extends AppVersionedObjectDao<AppResource> {
    Collection<AppResource> getResources(String filterString, AppDefinition appDefinition, String sort, Boolean desc, Integer start, Integer rows);
    
    Long getResourcesCount(String filterString, AppDefinition appDefinition);
}
