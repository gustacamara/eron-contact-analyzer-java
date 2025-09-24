# Enron Contact Analyzer

Um analisador de contatos desenvolvido para extrair informações da base de e-mails **Enron Email Dataset**. Este projeto aplica conceitos práticos de teoria dos grafos para analisar redes de comunicação e extrair insights úteis sobre padrões de comunicação.

## Sobre o Projeto

O **Enron Contact Analyzer** é uma ferramenta que processa a famosa base pública de e-mails da Enron (disponível em https://www.cs.cmu.edu/~./enron/) e constrói um grafo direcionado e ponderado para análise de comunicações entre funcionários.

O grafo gerado considera:
- **Vértices**: Endereços de e-mail dos usuários
- **Arestas direcionadas**: Relação de comunicação (remetente → destinatário)
- **Pesos**: Frequência de comunicação entre dois usuários
- **Rótulos**: Endereços de e-mail como identificadores

## Funcionalidades

### Implementadas

#### Processamento de Dados
- **Leitura do Dataset**: Scanner automático dos diretórios de e-mail
- **Extração de Remetentes e Destinatários**: Parsing dos headers dos e-mails
- **Estrutura de Dados**: Classes para representação dos nós de dados brutos
- **Exportação CSV**: Geração de arquivo CSV com os dados processados

### Em Desenvolvimento

#### Construção do Grafo
- **Criação do Grafo Direcionado**: Implementação da estrutura de grafo ponderado a partir dos dados de e-mail

#### Análises Estatísticas
- **Contagem de Vértices**: Número total de usuários na rede
- **Contagem de Arestas**: Número total de conexões de comunicação
- **Top 20 Grau de Saída**: Usuários que mais enviam e-mails
- **Top 20 Grau de Entrada**: Usuários que mais recebem e-mails

#### Algoritmos de Busca
- **Busca em Profundidade (DFS)**: Verificação de conectividade entre usuários com retorno do caminho
- **Busca em Largura (BFS)**: Verificação de conectividade entre usuários com retorno do caminho
- **Tratamento de Ciclos**: Prevenção de loops infinitos nos algoritmos

#### Análises Avançadas
- **Distância por Arestas**: Encontrar todos os nós a uma distância D de um nó N
- **Caminho Crítico de Fluxo**: Implementação do algoritmo Dijkstra adaptado para encontrar o caminho de maior custo acumulado (maior dependência)

## Tecnologias Utilizadas

- **Java**: Linguagem principal de desenvolvimento
- **Java NIO**: Para manipulação eficiente de arquivos
- **Collections Framework**: Para estruturas de dados

## Dados de Entrada

O projeto utiliza o **Enron Email Dataset**, que contém:
- Mais de 500.000 mensagens de e-mail
- E-mails de aproximadamente 150 usuários
- Estrutura hierárquica de pastas por usuário
- Subpastas organizadas por tipo (sent_mail, inbox, etc.)

## Próximos Passos

1. **Implementação do Grafo**: Criar estrutura de grafo direcionado ponderado
2. **Métricas Básicas**: Implementar contadores de vértices e arestas
3. **Rankings**: Desenvolver análises de grau de entrada e saída
4. **Algoritmos de Busca**: Implementar DFS e BFS com detecção de caminhos
5. **Análises de Distância**: Implementar busca por distância específica
6. **Algoritmo de Caminho Crítico**: Adaptar Dijkstra para maior custo acumulado

## Contribuições

Este é um projeto acadêmico focado no aprendizado prático de teoria dos grafos e análise de redes sociais.

---

*Desenvolvido como parte do estudo de estruturas de dados e algoritmos aplicados a redes sociais.*