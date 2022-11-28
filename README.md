# Sobre este repositório

Projeto desenvolvido durante a disciplina de Programação Orientada a Objetos, ministrada pelo prof. Felipe Islame, durante 
o semestre 2022.2, na  UFBA.

O projeto simula uma Urna Eleitoral.

<p align="center">

  <a href="https://github.com/matheusvictor/urna_eleitoral/stargazers">
    <img src="https://img.shields.io/github/stars/matheusvictor/urna_eleitoral" title="Stars" alt="Stars" /> 
  </a>

  <a href="https://github.com/matheusvictor/urna_eleitoral/network/members">
    <img src="https://img.shields.io/github/forks/matheusvictor/urna_eleitoral" title="Forks" alt="Forks" /> 
  </a>

  <a href="https://github.com/matheusvictor/urna_eleitoral/blob/main/LICENSE">
    <img src="https://img.shields.io/github/license/matheusvictor/urna_eleitoral?style=plastic" title="License" alt="License" /> 
  </a>

  <a href="#">
    <img src="https://img.shields.io/github/languages/count/matheusvictor/urna_eleitoral?style=plastic" title="Language Count" alt="Language Count" />
  </a>

  <a href="#">
    <img src="https://img.shields.io/github/languages/top/matheusvictor/urna_eleitoral?style=plastic" title="Top Language" alt="Top Language" />
  </a>

  <a href="https://github.com/matheusvictor/estudos_python/commits/master">
    <img src="https://img.shields.io/github/last-commit/matheusvictor/urna_eleitoral?style=plastic" title="Last Commit" alt="Last Commit" />
  </a>
</p>

<details>
    <summary><strong>Diagrama de Classes</strong></summary>

  - [Arquivo editável .drawio](https://github.com/matheusvictor/urna_eleitoral/blob/main/src/diagramas/diagrama-de-classes.drawio)
  - [Arquivo em PDF]()

</details>

### [Contribuidores](https://github.com/matheusvictor/urna_eleitoral/graphs/contributors)

Este projeto foi desenvolvido pelos estudantes listados.

# Mini-manual de como utilizar o sistema
- Para rodar o sistema basta fazer o git clone do repositório no GitHub, e rodar o Main.java na branch principal (a branch main). 
- Após iniciar  a execução do programa, todas as  ações por parte do usuário devem ser feitas através do teclado, escolhendo entre as opções apresentadas no console. Logo no início, duas opções serão disponibilizadas. Aperte 0 para iniciar a eleição e ser apresentado a mais opções, ou aperte 99 para encerrar o programa (a opção de encerrar o programa, com o número 99 ficará disponível durante toda a execução do programa. Após o início da eleição, outras 9 opções ficarão disponíveis para escolha, são elas: 
  - Imprimir zerésima – assim como na vida real, serve como um comprovante de que a urna eletrônica não possui votos computados, essa opção deve ser selecionada antes do início das votações, e imprime o número de votos válidos, nulos e em branco – 
  - Iniciar votações – inicia o fluxo de votos, seguindo a ordem: deputado estadual > deputado federal > senador > governador > presidente –;
  - Ver lista de candidatos – possibilita que o usuário escolha quais cargos ele gostaria de obter a listagem, que mostra o nome, cargo e número dos candidatos –
  - Imprimir relatório – o relatório deve ser impresso após o término da votação, uma vez que mostra o número total de votos de todos os candidatos, votos nulos/em branco, e os candidatos eleitos –
  - Zerar urna – faz com que todos os votos sejam zerados, possibilitando um novo início de votação –.

- Já na fase de votações, o número 0 é usado como “Branco”, e os botões de “Confirma” e “Corrige” são apresentados de forma abstrata após o usuário dar Enter no número do candidato que gostaria de votar. Caso vote em um candidato com número inexistente, o voto é contabilizado como nulo (antes sempre há uma confirmação para que o usuário confirme ou corrija o voto). Após o eleitor votar em todos os cargos possíveis, serão apresentadas duas opções: continuar votando ou voltar ao menu inicial (com as 10 opções já citadas). O programa encerra quando o usuário escolher a opção 99 (Encerrar o programa).
