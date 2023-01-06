package sg.edu.nus.iss.app.workshop13.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class IOUtil {
    public static void createDir(String path) {
        File dir = new File(path);
        boolean isCreated = dir.mkdir();
        if (isCreated) {
            String osName = System.getProperty("os.name");
            if(!osName.contains("Windows")) {
                try {
                    String perm = "rwxrwx---";
                    Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);
                    Files.setPosixFilePermissions(dir.toPath(), permissions);
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
