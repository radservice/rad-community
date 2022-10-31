package net.purwana.rads.apps.userview.model;

import net.purwana.rads.directory.model.User;

public abstract class Permission extends ExtElement {
    
    public static final String DEFAULT = "default"; 
    
    private User currentUser;

    /**
     * Gets current logged in user. 
     * @return NULL if anonymous.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets current logged in user.
     * @param currentUser 
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Check the current user is authorized to proceed.
     * @return 
     */
    public abstract boolean isAuthorize();
}
