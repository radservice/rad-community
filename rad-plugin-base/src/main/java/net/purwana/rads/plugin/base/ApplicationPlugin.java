package net.purwana.rads.plugin.base;

import java.util.Map;

/**
 * Interface for Process Tool Plugin and Form Post Submission Processing Plugin. 
 * 
 */
public interface ApplicationPlugin {

    /**
     * To execute the extra processing.
     * 
     * A net.purwana.rads.workflow.model.WorkflowAssignment object is passed as "workflowAssignment" property when it is available.
     * 
     * @param props
     * @return is not used for now
     */
    Object execute(Map props);
}
