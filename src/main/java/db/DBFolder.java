package db;

import models.File;
import models.Folder;

import java.util.List;

public class DBFolder extends DBHelper {

    public static List<Folder> getAll(){
        return getAll(Folder.class);
    }

    public static Folder find(int id){
        return find(id,Folder.class);
    }

    public static List<File> getFiles(Folder folder){
        return findManyListForOne(folder, File.class, "folder");
    }

}
