import db.DBFile;
import db.DBFolder;
import models.ExtensionType;
import models.File;
import models.Folder;

public class Runner {

    public static void main(String[] args) {

        Folder folder = new Folder("Home");
        DBFolder.save(folder);

        File file = new File("Hello world", ExtensionType.JAVA, 123, folder);
        DBFile.save(file);

        System.exit(0);
    }
}
