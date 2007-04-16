/*
 * @(#)AbstractProject.java  1.1.1  2006-04-11
 *
 * Copyright (c) 1996-2006 by the original authors of JHotDraw
 * and all its contributors ("JHotDraw.org")
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JHotDraw.org ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * JHotDraw.org.
 */

package org.jhotdraw.application;

import org.jhotdraw.application.action.SelectAllAction;
import java.io.*;
import java.util.*;
import org.jhotdraw.util.*;
import javax.swing.*;
import java.util.concurrent.*;
import java.util.prefs.*;
/**
 * AbstractProject.
 *
 *
 * @author Werner Randelshofer
 * @version 1.1.1 2006-04-11 Fixed project file preferences.
 * <br>1.1 2006-02-16 Support for preferences added.
 * <br>1.0 January 3, 2006 Created.
 */
public abstract class AbstractProject extends JPanel implements Project {
    private WindowManager windowManager;
    /**
     * The file chooser used for saving the project.
     * Has a null value, if the file chooser has not been used yet.
     */
    protected JFileChooser saveChooser;
    /**
     * The file chooser used for opening the project.
     * Has a null value, if the file chooser has not been used yet.
     */
    protected JFileChooser openChooser;
    /**
     * The project file.
     * Has a null value, if the project has not been loaded from a file
     * or has not been saved yet.
     */
    protected File file;
    /**
     * The executor used to perform background tasks for the Project in a
     * controlled manner. This executor ensures that all background tasks
     * are executed sequentually.
     */
    protected Executor executor;
    /**
     * Hash map for storing project actions.
     */
    private HashMap actions;
    /**
     * This is set to true, if the project has unsaved changes.
     */
    private boolean hasUnsavedChanges;
    /**
     * The preferences of the project.
     */
    private Preferences prefs;
    /**
     * This id is used to make multiple open projects from the same project file
     * identifiable.
     */
    private int multipleOpenId = 1;
    /**
     * This is set to true, if the project is showing.
     */
    private boolean isShowing;
    
    /**
     * Creates a new instance.
     */
    public AbstractProject() {
    }
    
    public void init() {
        prefs = Preferences.userNodeForPackage(getClass());
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        
        setLayout(new java.awt.BorderLayout());
        
    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    public JComponent getComponent() {
        return this;
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File newValue) {
        File oldValue = file;
        file = newValue;
        if (prefs != null && newValue != null) {
            prefs.put("projectFile", newValue.getPath());
        }
        firePropertyChange("file", oldValue, newValue);
    }
    
    /**
     * Gets the open file chooser for the project.
     */
    public JFileChooser getOpenChooser() {
        if (openChooser == null) {
            openChooser = createOpenChooser();
        }
        return openChooser;
    }
    
    protected JFileChooser createOpenChooser() {
        JFileChooser c = new JFileChooser();
        if (prefs != null) {
            c.setSelectedFile(new File(prefs.get("projectFile", System.getProperty("user.home"))));
        }
        return c;
    }
    
    /**
     * Gets the save file chooser for the project.
     */
    public JFileChooser getSaveChooser() {
        if (saveChooser == null) {
            saveChooser = createSaveChooser();
        }
        return saveChooser;
    }
    
    protected JFileChooser createSaveChooser() {
        JFileChooser c = new JFileChooser();
        if (prefs != null) {
            c.setCurrentDirectory(new File(prefs.get("projectFile", System.getProperty("user.home"))));
        }
        return c;
    }
    /**
     * Returns true, if the project has unsaved changes.
     * This is a bound property.
     */
    public boolean hasUnsavedChanges() {
        return hasUnsavedChanges;
    }
    protected void setHasUnsavedChanges(boolean newValue) {
        boolean oldValue = hasUnsavedChanges;
        hasUnsavedChanges = newValue;
        firePropertyChange("hasUnsavedChanges", oldValue, newValue);
    }
    /**
     * Gets rid of all the resources of the project.
     * No other methods should be invoked on the project afterwards.
     */
    public void dispose() {
        
    }
    /**
     * Returns the action with the specified id.
     */
    public Action getAction(String id) {
        return (actions == null) ? null : (Action) actions.get(id);
    }
    
    /**
     * Puts an action with the specified id.
     */
    public void putAction(String id, Action action) {
        if (actions == null) {
            actions = new HashMap();
        }
        if (action == null) {
            actions.remove(id);
        } else {
            actions.put(id, action);
        }
    }
    /**
     * Executes the specified runnable on the worker thread of the project.
     * Execution is perfomred sequentially in the same sequence as the
     * runnables have been passed to this method.
     */
    public void execute(Runnable worker) {
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }
        executor.execute(worker);
    }
    
    public void setMultipleOpenId(int newValue) {
        int oldValue = multipleOpenId;
        multipleOpenId = newValue;
        firePropertyChange("multipleOpenId", oldValue, newValue);
    }
    
    public int getMultipleOpenId() {
        return multipleOpenId;
    }
    
    public void setShowing(boolean newValue) {
        boolean oldValue = isShowing;
        isShowing = newValue;
        firePropertyChange("showing", oldValue, newValue);
    }
    public boolean isShowing() {
        return isShowing;
    }
    
    public void markChangesAsSaved() {
        setHasUnsavedChanges(false);
    }
    
    public void setWindowManager(WindowManager newValue) {
        WindowManager oldValue = windowManager;
        windowManager = newValue;
        firePropertyChange("windowManager", oldValue, newValue);
    }
    
    public WindowManager getWindowManager() {
        return windowManager;
    }
}