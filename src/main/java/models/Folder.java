package models;

import db.IDB;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "folders")
public class Folder implements IDB {

    private int id;
    private String name;
    private List<File> files;
    private User user;

    public Folder(){}

    public Folder(String name, User user){
        this.name = name;
        this.user = user;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    public List<File> getFiles() {
        return this.files;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
