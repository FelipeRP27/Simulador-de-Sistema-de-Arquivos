import java.util.*;

public class Directory {
    private String name;
    private Map<String, FileEntry> files = new HashMap<>();
    private Map<String, Directory> subdirectories = new HashMap<>();

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, FileEntry> getFiles() {
        return files;
    }

    public Map<String, Directory> getSubdirectories() {
        return subdirectories;
    }
}