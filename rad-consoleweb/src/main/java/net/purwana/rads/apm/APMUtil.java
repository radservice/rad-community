package net.purwana.rads.apm;

public class APMUtil {
    public static Boolean isGlowrootAvailable() {
        return false;
    }
    
    public static void setTransactionName(String name, Integer priority) {
        if (isGlowrootAvailable()) {
            //do nothing
        }
    }
}
