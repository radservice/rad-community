package net.purwana.rads.plugin.base;

import java.util.Map;
import net.purwana.rads.plugin.property.model.PropertyEditable;

/**
 * A base abstract class to develop a Process Tool Plugin and Form Post Submission Processing Plugin. 
 * 
 */
public abstract class DefaultApplicationPlugin extends ExtDefaultPlugin implements ApplicationPlugin, PropertyEditable {
    
    /**
     * To execute the extra processing.
     * 
     * A net.purwana.rads.workflow.model.WorkflowAssignment object is passed as "workflowAssignment" property when it is available.
     * 
     * @param props
     * @return is not used for now
     */
    @Override
    public abstract Object execute(Map props);
}
