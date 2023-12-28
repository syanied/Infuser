package Infuser;

import Infuser.Mechanics.CheckRun;
import Infuser.Mechanics.PluginLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * Copyright 2023, Syanide, All rights reserved.
 * <p>
 * Main class for the Infuser Application version x1.0
 * @author Syanide.
 *
 */

public class Infuser {

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException {

        new CheckRun();
        new PluginLoader(new File("Plugins"));
    }
}
