package net.purwana.rads.workflow.model.service;

import net.purwana.rads.workflow.model.*;
import java.util.Collection;

public interface WorkflowActivityAssigner {
	
	public Collection<String> getActivityAssignments(WorkflowAssignment newAssignment, String requesterId);

}
