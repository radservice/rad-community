package net.purwana.rads.apps.util;

import net.purwana.rads.apps.workflow.security.AuthenticationTokenWrapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;

public class RadSecurityContextImpl extends SecurityContextImpl {

    @Override
    public void setAuthentication(Authentication authentication) {
        
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && !(authentication instanceof AuthenticationTokenWrapper)) {
            authentication = new AuthenticationTokenWrapper(authentication);
        }
        
        super.setAuthentication(authentication);
    }
}
