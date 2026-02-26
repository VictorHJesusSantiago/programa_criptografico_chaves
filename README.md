<div align="center">

<img src="https://cdn-icons-png.flaticon.com/512/2092/2092663.png" alt="Cifra Híbrida Logo" width="110" />

# 🔒 Programa de Cifra Híbrida — RSA + AES

**Uma aplicação de desktop em Java Swing para demonstrar a criptografia híbrida,**
**combinando chaves assimétricas (RSA) e simétricas (AES) para proteger mensagens.**

<br>

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-GUI-007396?style=for-the-badge&logo=java&logoColor=white)
![RSA](https://img.shields.io/badge/RSA-Assimétrico-8B0000?style=for-the-badge)
![AES](https://img.shields.io/badge/AES-Simétrico-1B5E20?style=for-the-badge)
![JCA](https://img.shields.io/badge/JCA-Java%20Cryptography-blueviolet?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completo-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

</div>

---

## 📚 Tabela de Conteúdos

> Navegue rapidamente pelas seções do projeto.

| # | Seção |
|:-:|:------|
| 1 | [📖 Sobre o Projeto](#-sobre-o-projeto) |
| 2 | [🔑 O Conceito de Cifra Híbrida](#-o-conceito-de-cifra-híbrida) |
| 3 | [✨ Funcionalidades](#-funcionalidades) |
| 4 | [🛠️ Pilha de Tecnologias](#️-pilha-de-tecnologias) |
| 5 | [📂 Estrutura do Repositório](#-estrutura-do-repositório) |
| 6 | [🚀 Como Executar](#-como-executar) |
| 7 | [🤝 Como Contribuir](#-como-contribuir) |
| 8 | [👨‍💻 Autor](#-autor) |
| 9 | [📄 Licença](#-licença) |

---

## 📖 Sobre o Projeto

> **Cifra Híbrida** é uma ferramenta gráfica que implementa um sistema de **criptografia híbrida** — o mesmo conceito utilizado em protocolos modernos como HTTPS e TLS.

O projeto combina o melhor de dois mundos: a **segurança** da criptografia assimétrica (RSA) com a **eficiência** da criptografia simétrica (AES), demonstrando de forma prática como mensagens podem ser protegidas e transmitidas com segurança.

A lógica principal (`CifraHibrida.java`) e a interface gráfica (`CifraHibridaGUI.java`) permitem gerar um par de chaves RSA, cifrar e decifrar mensagens com apenas alguns cliques.

---

## 🔑 O Conceito de Cifra Híbrida

> A criptografia puramente **assimétrica (RSA)** é segura, mas lenta para grandes volumes de dados. A criptografia puramente **simétrica (AES)** é rápida, mas apresenta o problema de como compartilhar a chave secreta com segurança. A **Cifra Híbrida** resolve ambos os problemas simultaneamente.

### 🔐 Processo de Cifragem (Enviar Mensagem)

```
1. 🎲 Gera-se uma CHAVE DE SESSÃO AES aleatória (simétrica)
         ↓
2. 📝 A MENSAGEM ORIGINAL é cifrada com a chave AES  →  [ TEXTO CIFRADO (AES) ]
         ↓
3. 🔑 A CHAVE AES é cifrada com a CHAVE PÚBLICA RSA  →  [ CHAVE CIFRADA (RSA) ]
         ↓
4. 📤 [ TEXTO CIFRADO (AES) ] + [ CHAVE CIFRADA (RSA) ] são enviados ao destinatário
```

### 🔓 Processo de Decifragem (Receber Mensagem)

```
1. 📥 Destinatário recebe [ TEXTO CIFRADO (AES) ] + [ CHAVE CIFRADA (RSA) ]
         ↓
2. 🔑 A CHAVE PRIVADA RSA decifra a CHAVE CIFRADA  →  [ CHAVE AES RECUPERADA ]
         ↓
3. 📝 A CHAVE AES decifra o TEXTO CIFRADO  →  [ MENSAGEM ORIGINAL ]
```

### ⚖️ Por que Híbrida?

| Algoritmo | Vantagem | Limitação | Papel na Cifra Híbrida |
|:----------|:--------:|:---------:|:-----------------------|
| **RSA** (Assimétrico) | 🔐 Alta segurança | 🐢 Lento para dados grandes | Cifra apenas a **chave AES** (pequena). |
| **AES** (Simétrico) | ⚡ Muito eficiente | 🤝 Problema de troca de chave | Cifra a **mensagem** (rápido e seguro). |

---

## ✨ Funcionalidades

| Ícone | Funcionalidade | Descrição |
|:-----:|:---------------|:----------|
| 🗝️ | **Gerar Chaves** | Cria um par de chaves RSA — Pública e Privada — exibidas na interface. |
| 🔒 | **Cifrar** | Recebe uma mensagem e a Chave Pública RSA para executar o processo de cifragem híbrida. |
| 🔓 | **Decifrar** | Recebe os dados cifrados e a Chave Privada RSA para recuperar a mensagem original. |
| 🧹 | **Limpar** | Limpa todas as áreas de texto da interface. |
| 🚪 | **Sair** | Fecha a aplicação. |

---

## 🛠️ Pilha de Tecnologias

| Tecnologia | Função no Projeto |
|:-----------|:------------------|
| **Java** | Linguagem principal — toda a lógica de criptografia e interface gráfica. |
| **Java Swing** | Construção da interface gráfica desktop (`JFrame`, `JTextArea`, botões). |
| **JCA — RSA** | `KeyPairGenerator` para geração do par de chaves e `Cipher` para cifra assimétrica. |
| **JCA — AES** | `KeyGenerator` para a chave de sessão e `Cipher` para cifra simétrica da mensagem. |
| **Apache NetBeans** | IDE utilizada no desenvolvimento (arquivos `nbproject/` e `build.xml` incluídos). |
| **Apache Ant** | Sistema de build via `build.xml` utilizado pelo NetBeans. |

---

## 📂 Estrutura do Repositório

```plaintext
programa_criptografico_chaves/
│
├── 📄 README.md                           # 📖 Este arquivo
│
└── 📁 teste_cripto/
    │
    ├── 📄 build.xml                       # ⚙️  Arquivo de build (Apache Ant)
    ├── 📄 manifest.mf                     # 📋 Manifesto — aponta a classe principal
    │
    ├── 📁 src/
    │   ├── 📄 CifraHibrida.java           # 🔐 Lógica de criptografia RSA + AES ← CORE
    │   └── 📄 CifraHibridaGUI.java        # 🖥️  Interface gráfica Swing ← CORE
    │
    ├── 📁 dist/
    │   └── 📄 teste_cripto.jar            # 🚀 JAR executável (pronto para uso)
    │
    └── 📁 nbproject/                      # ⚙️  Arquivos de configuração do NetBeans
```

---

## 🚀 Como Executar

### 📋 Pré-requisitos

| Requisito | Detalhe |
|:----------|:--------|
| **JRE / JDK** | Versão **8 ou superior** instalada e configurada no `PATH`. |
| **Apache NetBeans** | *(Opcional)* Necessário apenas para compilar pelo método 2. |
| **Git** | Para clonar o repositório. |

---

### 🟢 Opção 1 — JAR Executável (Recomendado)

> O arquivo `.jar` já está **compilado e pronto para uso** na pasta `dist/`.

**1. Clone o repositório:**

```bash
git clone https://github.com/VictorHJesusSantiago/programa_criptografico_chaves.git
cd programa_criptografico_chaves/teste_cripto/dist
```

**2. Execute o JAR via terminal:**

```bash
java -jar teste_cripto.jar
```

> 💡 **Atalho:** Na maioria dos sistemas operacionais, basta dar **clique duplo** no arquivo `teste_cripto.jar` para abrir a interface gráfica diretamente.

---

### 🔧 Opção 2 — Compilar pela IDE NetBeans

```
1. Abra o Apache NetBeans IDE
2. File → Open Project...
3. Selecione a pasta 'teste_cripto'
4. Clique em "Run Project" ou pressione F6
```

> A IDE compila e executa `CifraHibridaGUI.java` automaticamente.

---

### 🎯 Como Usar a Aplicação

| Passo | Ação |
|:-----:|:-----|
| 1️⃣ | Clique em **Gerar Chaves** para criar o par RSA (Pública e Privada). |
| 2️⃣ | Digite a mensagem que deseja proteger no campo de entrada. |
| 3️⃣ | Clique em **Cifrar** — a mensagem cifrada (AES) e a chave de sessão cifrada (RSA) serão exibidas. |
| 4️⃣ | Clique em **Decifrar** usando a Chave Privada para recuperar a mensagem original. |
| 5️⃣ | Use **Limpar** para resetar todos os campos e iniciar um novo ciclo. |

---

## 🤝 Como Contribuir

> Contribuições são muito bem-vindas! Siga as etapas abaixo para colaborar de forma organizada.

| Passo | Ação | Comando |
|:-----:|:-----|:--------|
| 1️⃣ | **Fork** | Crie um fork do repositório para a sua conta. | — |
| 2️⃣ | **Branch** | Crie sua feature branch a partir da `main`. | `git checkout -b feature/NovaFeature` |
| 3️⃣ | **Commit** | Salve as alterações com mensagem clara e semântica. | `git commit -m 'feat: Adiciona NovaFeature'` |
| 4️⃣ | **Push** | Envie a branch para o repositório remoto. | `git push origin feature/NovaFeature` |
| 5️⃣ | **Pull Request** | Abra um PR detalhando as mudanças realizadas. | — |

<div align="center">

<br>

**Se este projeto foi útil para os seus estudos, deixe uma estrela ⭐️ no repositório!**

</div>

---

## 👨‍💻 Autor

<div align="center">

<br>

**Victor H. J. Santiago**

<br>

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/VictorHJesusSantiago)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/victor-henrique-de-jesus-santiago/)

</div>

---

## 📄 Licença

<div align="center">

Este projeto está distribuído sob a **Licença MIT**.
Consulte o arquivo [`LICENSE`](./LICENSE) no repositório para mais informações.

![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

</div>

---

<div align="center">

*Feito com 🔒 e Java por **Victor H. J. Santiago***

</div>
