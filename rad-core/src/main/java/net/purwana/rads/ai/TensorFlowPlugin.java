package net.purwana.rads.ai;

public interface TensorFlowPlugin {
    
    public TensorFlowInput[] getInputClasses();
    
    public TensorFlowPostProcessing[] getPostProcessingClasses();
}
