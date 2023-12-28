package Infuser.Mechanics;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

/**
 * Plugin loader for Infuser Application version x1.0,
 * Load and enables all the available Plugin(s) inside the Plugins folder.
 *
 * @author Syanide.
 */

public class PluginLoader {

    private URL[] plugins;
    private ClassLoader loader;

    private static final String RESET_COLOR_STRING = "\u001B[0m";
    private static final String ERROR_COLOR_STRING = "\u001B[31m";

    public PluginLoader(@NotNull File directory) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {

        plugins = null;
        loader = null;

        if(directory.exists()) {
            if(directory.isDirectory()) {

                for(File file : Objects.requireNonNull(directory.listFiles())) {
                    if(getFileExtension(file).equals(".jar")) {

                        URL path = file.toURI().toURL();
                        loadJarMainClass(path);
                    }
                }

            } else {
               System.out.println(ERROR_COLOR_STRING+"[!] Error: File passed to load Plugins from is not a directory."+RESET_COLOR_STRING);
            }

        } else {
            System.out.println(ERROR_COLOR_STRING+"[!] Error: File passed to load Plugins from does not exist."+RESET_COLOR_STRING);
        }
    }

    private void loadJarMainClass(URL path) throws InstantiationException, IllegalAccessException, InvocationTargetException, IOException {

        plugins = new URL[] {path};

        loader = new URLClassLoader(plugins);

        Class<?> clazz = null;

        try {

            InputStream inputStream = loader.getResourceAsStream("PluginInfo.ins");
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            for(String line; (line = reader.readLine()) != null;) {

                if(line.startsWith("Main")) {
                    String[] mainClassString = line.split(" ");

                    clazz = loader.loadClass(mainClassString[1]);
                }
            }

            assert clazz != null;
            clazz.getMethod("enable").invoke(clazz.getConstructor().newInstance());

        } catch (NoSuchMethodException | ClassNotFoundException e) {

            if(e instanceof NoSuchMethodException) {

                System.out.println(ERROR_COLOR_STRING+"[!] Error: Enable method in "+clazz.getName()+" class was not found, Failed to boot up the Plugin: "+path.getFile()+RESET_COLOR_STRING);
            } else {

                System.out.println(ERROR_COLOR_STRING+"[!] Error: Main/Plugin class was not found in "+path.getFile()+" JAR, Failed to initiate the Plugin."+RESET_COLOR_STRING);
            }
        }
    }

    private String getFileExtension(File file) {

        int lastIndexOf = file.getName().lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return file.getName().substring(lastIndexOf);
    }
}
