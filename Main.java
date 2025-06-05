import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileSystemSimulator fs = new FileSystemSimulator();
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println("Bem-vindo ao Simulador de Sistema de Arquivos");
        System.out.println("Digite comandos como em um terminal (digite 'help' para ajuda, 'exit' para sair)");

        while (true) {
            System.out.print("> ");
            input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;
            if (input.equalsIgnoreCase("help")) {
                printHelp();
                continue;
            }
            if (input.equalsIgnoreCase("log")) {
                fs.showJournal();
                continue;
            }

            try {
                String[] parts = input.split(" ", 3);
                String cmd = parts[0];

                switch (cmd) {
                    case "mkdir":
                        fs.createDirectory(parts[1]);
                        break;
                    case "touch":
                        fs.createFile(parts[1], "Conteúdo padrão");
                        break;
                    case "rm":
                        fs.deleteFile(parts[1]);
                        break;
                    case "rmdir":
                        fs.deleteDirectory(parts[1]);
                        break;
                    case "mv":
                        if (!fs.renameFile(parts[1], parts[2])) {
                            fs.renameDirectory(parts[1], parts[2]);
                        }
                        break;
                    case "cp":
                        fs.copyFile(parts[1], parts[2]);
                        break;
                    case "ls":
                        fs.listDirectory(parts[1]);
                        break;
                    default:
                        System.out.println("Comando não reconhecido. Digite 'help' para ver os comandos.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao executar comando. Verifique a sintaxe.");
            }
        }

        System.out.println("Sistema encerrado.");
    }

    private static void printHelp() {
        System.out.println("Comandos disponíveis:");
        System.out.println("mkdir /caminho           - Criar diretório");
        System.out.println("touch /caminho/arquivo   - Criar arquivo");
        System.out.println("rm /caminho/arquivo      - Remover arquivo");
        System.out.println("rmdir /caminho/diretorio - Remover diretório");
        System.out.println("mv /origem /destino      - Renomear arquivo ou diretório");
        System.out.println("cp /origem /destino       - Copiar arquivo");
        System.out.println("ls /caminho              - Listar conteúdo do diretório");
        System.out.println("log                      - Mostrar operações registradas");
        System.out.println("exit                     - Sair do sistema");
    }
}