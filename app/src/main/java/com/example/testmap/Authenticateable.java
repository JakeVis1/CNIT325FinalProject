package com.example.testmap;

import java.io.File;

/**
 * This interface is used for teh CheckLogin method.
 * This method allows for the creation of an Authenticateable
 * instead of directly instantiating an object
 * implemented in the login class
 *
 * @author Jake Visniski
 */
public interface Authenticateable {
    public boolean CheckLogin(File file);
}
