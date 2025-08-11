# TODO (ativo)

## Alta prioridade
- [x] Frontend: testes de hooks
  - [x] `useTaskList` (lista, dependências, paginação, filtros)
  - [x] `useTaskFilters`
  - [x] `useTaskFormHandlers` e `useTaskFormData` (criar/editar recorrente)
- [ ] Backend: testes adicionais
  - [ ] Paginação: `/tasks/paginated` e variações (estabilizar com Testcontainers)
  - [ ] Filtro por categoria e recorrência (lista e paginado) (estabilizar com Testcontainers)
- [ ] E2E mínimo (UI): criar tarefa recorrente, completar, validar nova instância (Playwright)

## Média prioridade
- [x] Estrutura Maven multi-módulo (root agregador + `backend/`)
- [x] Otimização POMs: centralizar versões no pai (dependencyManagement/BOM), enxugar `backend/pom.xml`
- [ ] Containerização app completa (backend + frontend)
  - [ ] Dockerfile/Containerfile para backend
  - [ ] Dockerfile/Containerfile para frontend
  - [ ] Compose/Podman Compose unificado (app + mongo + nginx opcional)
- [ ] Segurança
  - [ ] Externalizar `jwt.secret` via env
  - [ ] CORS: restringir `allowed-origins`
  - [ ] Revisar políticas de senha/erros
  - [x] Garantir uso do `application.properties.example` e manter `application.properties` fora do git
- [ ] Performance
  - [ ] Índices Mongo para `userId`, `state.deleted`, `isRecurrent`, `metadata.position`, `metadata.category`
  - [ ] Ordenações consistentes por `metadata.position`

## Baixa prioridade
- [ ] CI: pipeline simples (build + testes back/front)
- [ ] Docs: atualizar README e `plan/` com fluxo dev (`make dev`, portas)
- [ ] Deploy: instruções básicas (dev/prod)

## Futuro
- [ ] Desenvolvimento mobile (fase dedicada)
- [ ] Integração MCP (fase dedicada)