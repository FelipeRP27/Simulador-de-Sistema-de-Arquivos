# 🗂️ Simulador de Sistema de Arquivos em Java

Feito por: Felipe Ramalho Perdigão - 2315063
           Mateus Tomaz Feijão - 2316163

Este projeto é um **simulador de sistema de arquivos** construído em Java puro, que replica comandos típicos de um terminal (como `mkdir`, `touch`, `ls`, `mv`, `rm`, etc.) usando **estruturas de dados em memória**, sem utilizar comandos nativos do Java para manipulação real do sistema de arquivos.

---

## 🎯 Objetivos

- Simular operações básicas de um sistema de arquivos hierárquico.
- Armazenar o estado dos arquivos e diretórios em estruturas de dados internas.
- Registrar todas as operações realizadas em um **journal (log de operações)**.
- **Não utilizar APIs de manipulação de arquivos do Java**, como `java.io.File`, `Files`, `Paths`, entre outras.

---

## 🚀 Funcionalidades

| Comando | Descrição |
|--------|----------|
| `mkdir /caminho` | Cria um diretório no caminho especificado |
| `touch /caminho/arquivo` | Cria um arquivo com conteúdo padrão |
| `rm /caminho/arquivo` | Remove um arquivo |
| `rmdir /caminho/diretorio` | Remove um diretório vazio |
| `mv /origem /destino` | Renomeia ou move um arquivo ou diretório |
| `cp /origem /destino` | Copia um arquivo |
| `ls /caminho` | Lista o conteúdo de um diretório |
| `log` | Exibe o histórico de operações realizadas |
| `exit` | Encerra o simulador |

---

## 🧠 Estrutura do Projeto

- `Main.java`: Interface de linha de comando para entrada de comandos.
- `FileSystemSimulator.java`: Núcleo do simulador, responsável por processar os comandos.
- `Directory.java`: Representa diretórios no sistema simulado.
- `FileEntry.java`: Representa arquivos com nome e conteúdo.
- `Journal.java`: Implementa o log de operações.

- ---

## 🛠️ Tecnologias Utilizadas

- Java 8+
- Estruturas de dados: `Map`, `List`, `HashMap`
- Lógica recursiva e iterativa para navegação de diretórios

---

## ▶️ Como Executar o Projeto

### ✅ Pré-requisitos

- Ter o **Java JDK 8** (ou superior) instalado
- Ter um terminal (Windows CMD, PowerShell, Git Bash, Terminal Linux/Mac, etc.)

### 📦 Compilando

1. Clone ou baixe este repositório
2. Acesse a pasta do projeto no terminal
3. Compile todos os arquivos `.java`:
