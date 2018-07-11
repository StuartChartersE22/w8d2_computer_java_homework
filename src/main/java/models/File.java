package models;

import db.IDB;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File implements IDB {

    private int id;
    private String name;
    private ExtensionType extension;
    private int size;
    private Folder folder;

    public File(){}

    public File(String name, ExtensionType extension, int size, Folder folder){
        this.name = name;
        this.extension = extension;
        this.size = size;
        this.folder = folder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Enumerated(value = EnumType.STRING)
    public ExtensionType getExtension() {
        return extension;
    }

    @Column(name = "size")
    public int getSize() {
        return size;
    }

    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    public Folder getFolder() {
        return folder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExtension(ExtensionType extension) {
        this.extension = extension;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
