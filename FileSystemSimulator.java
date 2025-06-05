import java.util.*;

public class FileSystemSimulator {
    private Directory root = new Directory("/");
    private Journal journal = new Journal();

    public void createFile(String path, String content) {
        String[] parts = path.split("/");
        Directory dir = navigateToDirectory(parts, parts.length - 1);
        if (dir != null) {
            dir.getFiles().put(parts[parts.length - 1], new FileEntry(parts[parts.length - 1], content));
            journal.addEntry("Arquivo criado: " + path);
        } else {
            journal.addEntry("Erro ao criar arquivo: Diretório não encontrado: " + path);
            System.out.println("Diretório não encontrado.");
        }
    }

    public void deleteFile(String path) {
        String[] parts = path.split("/");
        Directory dir = navigateToDirectory(parts, parts.length - 1);
        if (dir != null && dir.getFiles().remove(parts[parts.length - 1]) != null) {
            journal.addEntry("Arquivo removido: " + path);
        } else {
            journal.addEntry("Erro ao remover: Arquivo não encontrado: " + path);
            System.out.println("Arquivo não encontrado.");
        }
    }

    public boolean renameFile(String oldPath, String newPath) {
        String[] oldParts = oldPath.split("/");
        String[] newParts = newPath.split("/");

        Directory oldDir = navigateToDirectory(oldParts, oldParts.length - 1);
        Directory newDir = navigateToDirectory(newParts, newParts.length - 1);

        if (oldDir != null && newDir != null) {
            String oldName = oldParts[oldParts.length - 1];
            String newName = newParts[newParts.length - 1];

            FileEntry file = oldDir.getFiles().get(oldName);
            if (file != null) {

                FileEntry movedFile = new FileEntry(newName, file.getContent());
                newDir.getFiles().put(newName, movedFile);


                oldDir.getFiles().remove(oldName);

                journal.addEntry("Arquivo renomeado de " + oldPath + " para " + newPath);
                return true;
            }
        }
        return false;
    }


    public void createDirectory(String path) {
        String[] parts = path.split("/");
        Directory dir = root;
        for (int i = 1; i < parts.length; i++) {
            dir = dir.getSubdirectories().computeIfAbsent(parts[i], Directory::new);
        }
        journal.addEntry("Diretório criado: " + path);
    }

    public void deleteDirectory(String path) {
        String[] parts = path.split("/");
        Directory parent = navigateToDirectory(parts, parts.length - 1);
        String dirName = parts[parts.length - 1];
        if (parent != null && parent.getSubdirectories().containsKey(dirName)) {
            Directory dirToRemove = parent.getSubdirectories().get(dirName);
            if (dirToRemove.getFiles().isEmpty() && dirToRemove.getSubdirectories().isEmpty()) {
                parent.getSubdirectories().remove(dirName);
                journal.addEntry("Diretório removido: " + path);
            } else {
                journal.addEntry("Erro ao remover diretório: Diretório não está vazio: " + path);
                System.out.println("Diretório não está vazio.");
            }
        } else {
            journal.addEntry("Erro ao remover diretório: Diretório não encontrado: " + path);
            System.out.println("Diretório não encontrado.");
        }
    }

    public void renameDirectory(String oldPath, String newPath) {
        String[] oldParts = oldPath.split("/");
        String[] newParts = newPath.split("/");

        Directory oldParent = navigateToDirectory(oldParts, oldParts.length - 1);
        Directory newParent = navigateToDirectory(newParts, newParts.length - 1);
        String oldName = oldParts[oldParts.length - 1];
        String newName = newParts[newParts.length - 1];

        if (oldParent != null && oldParent.getSubdirectories().containsKey(oldName)) {
            Directory dir = oldParent.getSubdirectories().remove(oldName);
            if (newParent != null) {
                newParent.getSubdirectories().put(newName, dir);
                journal.addEntry("Diretório renomeado de " + oldPath + " para " + newPath);
            } else {
                oldParent.getSubdirectories().put(oldName, dir); // restaura
                journal.addEntry("Erro ao renomear diretório: Diretório de destino não encontrado: " + newPath);
                System.out.println("Diretório de destino não encontrado.");
            }
        } else {
            journal.addEntry("Erro ao renomear diretório: Diretório não encontrado: " + oldPath);
            System.out.println("Diretório não encontrado.");
        }
    }

    public void copyFile(String sourcePath, String destPath) {
        String[] sourceParts = sourcePath.split("/");
        String[] destParts = destPath.split("/");

        Directory sourceDir = navigateToDirectory(sourceParts, sourceParts.length - 1);
        Directory destDir = navigateToDirectory(destParts, destParts.length - 1);

        if (sourceDir != null && destDir != null) {
            FileEntry file = sourceDir.getFiles().get(sourceParts[sourceParts.length - 1]);
            if (file != null) {
                FileEntry copied = new FileEntry(destParts[destParts.length - 1], file.getContent());
                destDir.getFiles().put(destParts[destParts.length - 1], copied);
                journal.addEntry("Arquivo copiado de " + sourcePath + " para " + destPath);
            } else {
                journal.addEntry("Erro ao copiar: Arquivo de origem não encontrado: " + sourcePath);
                System.out.println("Arquivo de origem não encontrado.");
            }
        } else {
            journal.addEntry("Erro ao copiar: Diretório inválido para " + sourcePath + " ou " + destPath);
            System.out.println("Diretório inválido.");
        }
    }

    public void listDirectory(String path) {
        String[] parts = path.split("/");
        Directory dir = navigateToDirectory(parts, parts.length);
        if (dir != null) {
            System.out.println("Conteúdo de " + path + ":");
            dir.getSubdirectories().keySet().forEach(d -> System.out.println("[DIR] " + d));
            dir.getFiles().keySet().forEach(f -> System.out.println("[FILE] " + f));
        } else {
            journal.addEntry("Erro ao listar diretório: Diretório não encontrado: " + path);
            System.out.println("Diretório não encontrado.");
        }
    }

    public void showJournal() {
        journal.printLog();
    }

    private Directory navigateToDirectory(String[] parts, int depth) {
        Directory dir = root;
        for (int i = 1; i < depth; i++) {
            dir = dir.getSubdirectories().get(parts[i]);
            if (dir == null) return null;
        }
        return dir;
    }
}