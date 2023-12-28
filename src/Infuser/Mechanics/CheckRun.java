package Infuser.Mechanics;

import Infuser.UI_UX.AppOpUI;
import Infuser.UI_UX.InfuserUI;
import Infuser.Utils.DataFileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Check runner on the Application startup.
 *
 * @author Syanide.
 */

public class CheckRun {

    public CheckRun() throws IOException {

        final File file = new File("data.ins");
        int line = 0;

        if (file.exists()) {

            for(int i = 0; i < 10; i++) {
                line = i;
            }

            try {

                if(!Objects.equals(Files.readAllLines(Paths.get("data.ins")).get(line), "")) {

                    new AppOpUI();
                    new InfuserUI().initComponents();
                } else {

                    reBuildDataFile();
                }

            } catch (IndexOutOfBoundsException e) {

                reBuildDataFile();
            }

        } else {

            reBuildDataFile();
            new CheckRun();
        }

        pluginsFolderCheck();
    }

    private void reBuildDataFile() throws IOException {

        new AppOpUI();
        DataFileUtil.createDataFile();
    }

    private void pluginsFolderCheck() {

        final File file = new File("Plugins");

        if (!file.exists()) {
            if(file.mkdirs())
                System.out.println("\u001B[32m\t"+"[*] Plugins folder created as it was not found during the application startup."+"\u001B[0m");
        }
    }
}
