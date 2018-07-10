package db;

import models.Folder;
import models.User;

import java.util.List;

public class DBUser extends DBHelper {

    public static List<Folder> getFolders(User user){
        return findManyListToOne(user, Folder.class, "user");
    }
}
