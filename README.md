<div align="center">

🔒 Programa de Cifra Híbrida (RSA + AES)

Uma aplicação de desktop em Java Swing para demonstrar a criptografia híbrida, utilizando chaves assimétricas (RSA) e simétricas (AES) para proteger mensagens.

</div>

<p align="center"> <img alt="Status do Projeto" src="https://img.shields.io/badge/Status-Completo-brightgreen?style=for-the-badge"> <img alt="Linguagem" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"> <img alt="UI" src="https://img.shields.io/badge/UI-Java%20Swing-blue?style=for-the-badge&logo=java"> <img alt="IDE" src="https://img.shields.io/badge/IDE-Apache%20NetBeans-blueviolet?style=for-the-badge&logo=apache-netbeans-ide"> </p>

---------------------------------------------------------------------------------------------------
📖 Sobre o Projeto

Este projeto é uma ferramenta gráfica (CifraHibridaGUI.java) que implementa um sistema de criptografia híbrida. Este método combina a segurança da criptografia assimétrica (RSA) com a eficiência da criptografia simétrica (AES).

A lógica principal (CifraHibrida.java) permite que um utilizador gere um par de chaves (Pública e Privada) e, em seguida, cifre e decifre mensagens de forma segura.

---------------------------------------------------------------------------------------------------
🔑 O Conceito de Cifra Híbrida
    
A criptografia puramente assimétrica (RSA) é lenta para grandes volumes de dados. A criptografia puramente simétrica (AES) é rápida, mas tem o problema de como partilhar a chave secreta de forma segura.

A Cifra Híbrida resolve ambos os problemas:
   
   1. Processo de Cifragem (Enviar Mensagem)

🔐 Gera-se uma chave de sessão (uma chave AES simétrica) aleatória.

📝 A mensagem original é cifrada usando esta chave AES (que é rápida).

🔑 A chave AES (que é pequena) é então cifrada usando a Chave Pública RSA do destinatário.

📤 O texto cifrado (AES) e a chave de sessão cifrada (RSA) são enviados juntos para o destinatário.

  2. Processo de Decifragem (Receber Mensagem)

🔑 O destinatário usa a sua Chave Privada RSA para decifrar a chave de sessão cifrada, recuperando a chave AES original.

📝 Com a chave AES agora em mãos, o destinatário decifra o texto cifrado, revelando a mensagem original.

---------------------------------------------------------------------------------------------------
✨ Funcionalidades

  A aplicação gráfica oferece as seguintes funções:

Gerar Chaves: Cria um par de chaves RSA (Pública e Privada).

Cifrar: Recebe uma mensagem e uma Chave Pública para executar o processo de cifragem híbrida.

Decifrar: Recebe os dados cifrados e a Chave Privada para reverter o processo e obter a mensagem original.

Limpar: Limpa todas as áreas de texto da interface.

Sair: Fecha a aplicação.

---------------------------------------------------------------------------------------------------
🛠️ Tecnologias Utilizadas

Java: Linguagem principal do projeto.

Java Swing: Para a construção da interface gráfica (GUI).

Java Cryptography Architecture (JCA):

RSA: Para a criptografia assimétrica das chaves.

AES: Para a criptografia simétrica da mensagem.

---------------------------------------------------------------------------------------------------
📂 Estrutura do Repositório
  O projeto foi desenvolvido na IDE Apache NetBeans e segue a sua estrutura padrão:

programa_criptografico_chaves/

│

├── teste_cripto/

│   ├── src/

│   │   ├── CifraHibrida.java     # Contém toda a lógica de criptografia

│   │   └── CifraHibridaGUI.java  # A classe principal com a interface Swing

│   │

│   ├── dist/

│   │   └── teste_cripto.jar      # O ficheiro JAR executável do projeto

│   │

│   ├── nbproject/                # Ficheiros de configuração do NetBeans

│   ├── build.xml                 # Ficheiro de build (Ant)

│   └── manifest.mf               # Manifesto que aponta a classe principal

│

└── README.md                     # Este ficheiro

---------------------------------------------------------------------------------------------------
💿 Como Executar o Projeto
  Existem duas formas fáceis de executar a aplicação:

  Opção 1: Usar o JAR Executável (Recomendado)

O ficheiro .jar já está compilado e pronto para ser executado.

Certifique-se de que tem o Java Runtime Environment (JRE) instalado no seu sistema.

Navegue até à pasta teste_cripto/dist/.

Execute o ficheiro JAR: java -jar teste_cripto.jar

(Ou, na maioria dos sistemas operativos, basta dar um clique duplo no ficheiro teste_cripto.jar).

Opção 2: Compilar pela IDE (NetBeans)
  Como este é um projeto NetBeans, a forma mais fácil de o compilar é:

Abra o Apache NetBeans IDE.

Vá a File > Open Project...

Selecione a pasta teste_cripto.

Clique no botão "Run Project" (ou prima F6). A IDE irá compilar e executar a CifraHibridaGUI.java automaticamente.
