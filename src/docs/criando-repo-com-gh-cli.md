# Criar um repositório no GitHub usando o GitHub CLI (`gh`)

Este guia mostra como instalar o GitHub CLI no macOS com Homebrew, fazer login na sua conta do GitHub e criar um novo repositório a partir de um projeto local.

---

## 1. Instalar o Homebrew

Se você ainda não tiver o Homebrew, instale com o comando oficial:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Depois, siga as instruções exibidas no terminal para adicionar o `brew` ao seu `PATH`.

Para testar:

```bash
brew --version
```

---

## 2. Instalar o GitHub CLI

Com o Homebrew instalado, rode:

```bash
brew install gh
```

Para verificar se deu certo:

```bash
gh --version
```

---

## 3. Fazer login na sua conta do GitHub

Autentique-se com:

```bash
gh auth login
```

Durante o processo, normalmente você vai escolher:

- **GitHub.com**
- Autenticação pelo **navegador**
- Protocolo Git: **SSH** ou **HTTPS**

Ao final, o `gh` ficará autenticado na sua conta.

---

## 4. Preparar o projeto local

Entre na pasta do seu projeto e, se ainda não houver commit, crie um primeiro commit:

```bash
cd /caminho/para/seu/projeto
git add .
git commit -m "Initial commit"
```

> **Importante:** o comando `gh repo create --push` precisa que exista pelo menos um commit local.

---

## 5. Criar o repositório no GitHub

Agora crie o repositório remoto e envie o conteúdo local:

```bash
gh repo create NOME-DO-REPOSITORIO --public --source=. --remote=origin --push
```

### Exemplo

```bash
gh repo create meu-projeto --public --source=. --remote=origin --push
```

### Opções úteis

- `--public`: cria o repositório como público
- `--private`: cria o repositório como privado
- `--source=.`: usa o diretório atual como fonte
- `--remote=origin`: cria o remoto chamado `origin`
- `--push`: envia os commits locais para o GitHub

---

## 6. Conferir se o remoto foi criado

Depois, verifique os remotes configurados:

```bash
git remote -v
```

E veja o histórico de commits:

```bash
git log --oneline --decorate -n 3
```

---

## 7. Se o repositório já existir no GitHub

Se você já criou o repositório manualmente no site, basta conectar o remoto e fazer push:

```bash
git remote add origin https://github.com/SEU-USUARIO/NOME-DO-REPOSITORIO.git
git branch -M main
git push -u origin main
```

Se estiver usando SSH:

```bash
git remote add origin git@github.com:SEU-USUARIO/NOME-DO-REPOSITORIO.git
git branch -M main
git push -u origin main
```

---

## Resumo rápido

```bash
brew install gh
gh auth login
cd /caminho/para/seu/projeto
git add .
git commit -m "Initial commit"
gh repo create NOME-DO-REPOSITORIO --public --source=. --remote=origin --push
```

