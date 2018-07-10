package db;

import models.File;

import java.util.List;

public class DBFile extends DBHelper {

    public static List<File> getAll(){
        return getAll(File.class);
    }

    public static File find(int id){
        return find(id,File.class);
    }

    public static List<File> orderBySize(){
        return orderByCriterion("size", File.class, false);
    }
}
