package net.purwana.rads.apps.app.dao;

import java.util.Collection;
import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.apps.app.model.Message;

public interface MessageDao extends AppVersionedObjectDao<Message> {

    public Message loadByMessageKey(String messageKey, String locale, AppDefinition appDefinition);

    public Collection<Message> getMessageList(String filterString, String locale, AppDefinition appDefinition, String sort, Boolean desc, Integer start, Integer rows);

    public Long getMessageListCount(String filterString, String locale, AppDefinition appDefinition);

    public Collection<String> getLocaleList(AppDefinition appDefinition);
}
