package net.purwana.rads.apps.app.dao;

import java.util.Collection;
import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.apps.app.model.PluginDefaultProperties;

public interface PluginDefaultPropertiesDao extends AppVersionedObjectDao<PluginDefaultProperties> {

    public Collection<PluginDefaultProperties> getPluginDefaultPropertiesList(String filterString, AppDefinition appDefinition, String sort, Boolean desc, Integer start, Integer rows);

    public Long getPluginDefaultPropertiesListCount(String filterString, AppDefinition appDefinition);
}
