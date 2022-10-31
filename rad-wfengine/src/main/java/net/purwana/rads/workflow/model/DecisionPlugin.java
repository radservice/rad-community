package net.purwana.rads.workflow.model;

import java.util.Map;
import net.purwana.rads.plugin.property.model.PropertyEditable;

public interface DecisionPlugin extends PropertyEditable {
    
    DecisionResult getDecision(String processDefId, String processId, String routeId, Map<String, String> variables);
    
}
