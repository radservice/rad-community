package net.purwana.rads.plugin.base;

import java.util.Map;

public class MockRequest {
    
    public String getContextPath() {
        return "/rad";
    }
    
    public Object getAttribute(String name) {
        return null;
    }
    
    public String getParameter(String name) {
        return null;
    }
    
    public String[] getParameterValues(String name) {
        return null;
    }
    
    public Map<String, String[]> getParameterMap() {
        return null;
    }
}
