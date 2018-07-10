import db.DBFile;
import db.DBFolder;
import models.ExtensionType;
import models.File;
import models.Folder;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        Folder folder1 = new Folder("Home");
        DBFolder.save(folder1);

        Folder folder2 = new Folder("MyDocuments");
        DBFolder.save(folder2);

        Folder folder3 = new Folder("Shared");
        DBFolder.save(folder3);

        File file1 = new File("Hello world", ExtensionType.JAVA, 123, folder1);
        DBFile.save(file1);

        File file2 = new File("To do list", ExtensionType.TXT, 8, folder2);
        DBFile.save(file2);

        File file3 = new File("Project presentation", ExtensionType.PPT, 321, folder3);
        DBFile.save(file3);

        File file4 = new File("ComputerModel", ExtensionType.JAVA, 666, folder2);
        DBFile.save(file4);

        List<File> allFiles = DBFile.getAll();
        List<Folder> allFolders = DBFolder.getAll();

        File foundFile = DBFile.find(file3.getId());
        Folder foundFolder = DBFolder.find(folder2.getId());

        List<File> filesBySize = DBFile.orderBySize();

        List<File> associatedFiles = DBFolder.getFiles(folder2);

        folder1.setName("Desktop");
        DBFolder.update(folder1);

        DBFile.delete(file2);

        System.exit(0);
    }
}
