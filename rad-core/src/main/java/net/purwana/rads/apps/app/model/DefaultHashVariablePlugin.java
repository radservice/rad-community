package net.purwana.rads.apps.app.model;

import java.util.ArrayList;
import java.util.Collection;
import net.purwana.rads.commons.util.StringUtil;
import net.purwana.rads.plugin.base.ExtDefaultPlugin;

/**
 * A base abstract class to develop a Hash Variable plugin. 
 * 
 */
public abstract class DefaultHashVariablePlugin extends ExtDefaultPlugin implements HashVariablePlugin {
    
    /**
     * Escape special character in the value.
     * 
     * 
     * @return 
     */
    public String escapeHashVariableValue(String value) {
        return value;
    }
    
    /**
     * Escape special character in the value.
     * 
     * Default to escape Regex in the value
     * 
     * @return 
     */
    public String escapeHashVariable(String variable) {
        return StringUtil.escapeString(variable, StringUtil.TYPE_REGEX, null);
    }
    
    /**
     * List the possible syntax combination to populate in Hash Variable Assistants
     * in Property Editor
     * 
     * Default to "<i>prefix</i>.KEY"
     * 
     * @return 
     */
    public Collection<String> availableSyntax() {
        Collection <String> list = new ArrayList<String>();
        
        list.add(getPrefix() + ".KEY");
        
        return list;
    }
}
