# Enron Contact Analyzer

Um analisador de redes de comunicação desenvolvido para extrair insights da base de e-mails **Enron Email Dataset**. Este projeto implementa conceitos avançados de teoria dos grafos para analisar padrões de comunicação organizacional.

## Sobre o Projeto

O **Enron Contact Analyzer** é uma ferramenta completa que processa a famosa base pública de e-mails da Enron e constrói um grafo direcionado e ponderado para análise detalhada de redes de comunicação entre funcionários.

### Características do Grafo
- **Vértices**: Endereços de e-mail dos usuários (~150 usuários únicos)
- **Arestas direcionadas**: Relação de comunicação (remetente → destinatário)
- **Pesos**: Frequência de comunicação entre dois usuários

## Funcionalidades Implementadas

### Processamento de Dados
- **Scanner de Dataset**: Leitura automática dos diretórios de e-mail da Enron
- **Parser de Headers**: Extração de remetentes e destinatários dos e-mails
- **Geração de CSV**: Exportação dos dados processados para análise
- **Cache Inteligente**: Evita reprocessamento desnecessário dos dados

### Estrutura do Grafo
- **Grafo Direcionado Ponderado**: Implementação com lista de adjacências
- **Criação de Adjacências**: Suporte a pesos customizados
- **Indexação por E-mail**: Mapeamento eficiente de e-mails para índices
- **Visualização**: Impressão da matriz de adjacências

### Análises Estatísticas
- **Contagem de Vértices**: Número total de usuários na rede
- **Contagem de Arestas**: Número total de conexões de comunicação
- **Grau de Saída**: Análise de usuários que mais enviam e-mails
- **Grau de Entrada**: Análise de usuários que mais recebem e-mails
- **Top Rankings**: Mostra os 20 usuários que mais enviaram e receberam emails

### Algoritmos de Busca
- **Busca em Largura (BFS)**: Encontra caminho mais curto entre usuários
- **Busca em Profundidade (DFS)**: Verifica conectividade com retorno do caminho
- **Alcance por Distância**: Encontra todos os nós a uma distância específica
- **Prevenção de Ciclos**: Tratamento inteligente de loops

### Análises Avançadas
- **Algoritmo de Dijkstra Adaptado**: Caminho de maior dependência (custo acumulado)
- **Análise de Centralidade**: Identificação de usuários centrais na rede

## Arquitetura do Sistema

### Camadas da Aplicação
```
src/
├── app/
│   └── Main.java                 # Ponto de entrada da aplicação
├── Controllers/
│   ├── DatasetController.java    # Processamento do dataset
│   └── GraphController.java      # Operações do grafo
├── model/
│   ├── Graph.java               # Implementação do grafo
│   ├── Vertex.java              # Estrutura de vértices
│   ├── DatasetReader.java       # Leitor de e-mails
│   ├── RawDataNode.java         # Nó de dados brutos
│   └── FrequencyNode.java       # Nó de frequência
└── data/
  ├── maildir/                 # Dataset Enron (diretórios de e-mail)
  └── processed/               # Arquivos CSV processados
```

### Fluxo de Execução
1. **Processamento**: `DatasetController` processa e-mails → CSV
2. **Construção**: Criação do grafo a partir dos dados processados
3. **Análise**: Execução de algoritmos e geração de estatísticas
4. **Saída**: Relatórios e visualizações dos resultados

## Dataset

**Enron Email Dataset** utilizado:
- **Volume**: 500,000+ mensagens de e-mail
- **Usuários**: ~150 funcionários da Enron
- **Período**: Comunicações corporativas históricas
- **Estrutura**: Hierarquia de pastas por usuário/tipo

## Como Executar

```bash
# Compilar o projeto
javac -d out src/app/*.java src/Controllers/*.java src/model/*.java

# Executar a análise
java -cp out app.Main
```

## Saída Esperada

O sistema gera:
- Lista de adjacências do grafo
- Estatísticas de grau de entrada/saída
- Rankings dos top 20 usuários mais ativos
- Número total de vértices e arestas
- Resultados de buscas BFS/DFS entre usuários específicos

## Contribuições

Este projeto demonstra aplicação prática de:
- Teoria dos Grafos em problemas reais
- Análise de Redes Sociais
- Algoritmos de Busca e Caminho
- Processamento de Big Data em Java

---

*Desenvolvido como projeto acadêmico de estruturas de dados e algoritmos aplicados à análise de redes organizacionais.*