package net.purwana.rads.workflow.model;

import java.util.Map;
import net.purwana.rads.plugin.base.ExtDefaultPlugin;

/**
 * A base abstract class to develop a Deadline Plugin
 * 
 */
public abstract class DefaultDeadlinePlugin extends ExtDefaultPlugin implements DeadlinePlugin {
    
    /**
     * This is not used
     * 
     * @param props
     * @return 
     */
    @Override
    public final Object execute(Map props) {
        return evaluateDeadline(props);
    }
}
