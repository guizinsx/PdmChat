# PdmChat

App Android de troca de mensagens em tempo real, feito em **Kotlin** com **Firebase Realtime Database**.

> Projeto desenvolvido na faculdade, na disciplina de Programação para Dispositivos Móveis (PDM), em 2024.

## Funcionalidades

- Caixa de entrada que mostra só as mensagens enviadas para o usuário atual, em ordem cronológica
- Envio de mensagens com remetente, destinatário e texto (limite de 150 caracteres)
- Atualização automática da lista quando chega uma mensagem nova, sem recarregar a tela
- Data e hora de cada mensagem

## Tecnologias

- Kotlin
- Android SDK (mínimo API 25, alvo API 34)
- Firebase Realtime Database
- RecyclerView e View Binding

## Como funciona

As mensagens ficam no nó `messages` do Firebase. A tela principal escuta esse nó com um `ValueEventListener`, filtra as mensagens pelo destinatário e mostra a lista num `RecyclerView`. A tela de envio grava cada nova mensagem com `push()`, junto com o horário de envio.

## Como rodar

1. Clone o repositório e abra no Android Studio.
2. Para usar o seu próprio Firebase, crie um projeto no console do Firebase, ative o Realtime Database e troque o arquivo `app/google-services.json` pelo seu.
3. Rode o app num emulador ou celular com Android 7.1 ou superior.
