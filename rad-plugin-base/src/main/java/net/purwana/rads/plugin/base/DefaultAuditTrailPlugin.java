package net.purwana.rads.plugin.base;

import java.util.Map;
import net.purwana.rads.plugin.property.model.PropertyEditable;

/**
 * A base abstract class to develop a Audit Trail Plugin. 
 * 
 */
public abstract class DefaultAuditTrailPlugin extends ExtDefaultPlugin implements AuditTrailPlugin, PropertyEditable {
    
    /**
     * To execute the extra processing based on Audit Trail Event.
     * An net.purwana.rads.apps.app.model.AuditTrail object is passed as "auditTrail" property.
     * 
     * @param props
     * @return is not used for now.
     */
    @Override
    public abstract Object execute(Map props);
}
