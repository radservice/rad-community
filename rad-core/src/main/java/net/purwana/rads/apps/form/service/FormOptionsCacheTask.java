package net.purwana.rads.apps.form.service;

import java.util.concurrent.ScheduledFuture;
import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.apps.app.service.AppUtil;
import net.purwana.rads.commons.util.HostManager;
import net.purwana.rads.commons.util.LogUtil;

public class FormOptionsCacheTask implements Runnable {
    private String profile;
    private String cacheKey;
    private AppDefinition appDef;
    private ScheduledFuture scheduledFuture;
    
    public FormOptionsCacheTask(String profile, String cacheKey, AppDefinition appDef) {
        this.profile = profile;
        this.cacheKey = cacheKey;
        this.appDef = appDef;
    }

    public ScheduledFuture getScheduledFuture() {
        return scheduledFuture;
    }

    public void setScheduledFuture(ScheduledFuture scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }
    
    @Override
    public void run() {
        try {
            if (FormOptionsCacheAspect.syncPaused(cacheKey)) {
                scheduledFuture.cancel(true);
                if (LogUtil.isDebugEnabled(FormOptionsCacheAspect.class.getName())) {    
                    LogUtil.debug(FormOptionsCacheAspect.class.getName(), "Stop syncOptionsCache: " + cacheKey);
                }
            } else {
                AppUtil.setCurrentAppDefinition(appDef);
                HostManager.setCurrentProfile(profile);
                FormOptionsCacheAspect.syncOptionsCache(cacheKey);
            }
        } catch (Exception e) {
            LogUtil.error(FormOptionsCacheTask.class.getName(), e, "Profile : " + profile);
        }
    }
}