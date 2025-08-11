# TODO (ativo)

## Alta prioridade
- [ ] Frontend: testes unitários de serviços
  - [ ] `frontend/src/services/taskCrudService.js`
  - [ ] `frontend/src/services/taskQueryService.js`
  - [ ] `frontend/src/services/taskActionService.js`
- [ ] Frontend: testes de hooks
  - [ ] `useTaskList` (lista, dependências, paginação, filtros)
  - [ ] `useTaskFilters`
  - [ ] `useTaskFormHandlers` e `useTaskFormData` (criar/editar recorrente)
- [ ] Backend: testes adicionais
  - [ ] Paginação: `/tasks/paginated` e variações
  - [ ] Filtro por categoria e recorrência (lista e paginado)
- [ ] E2E mínimo (UI): criar tarefa recorrente, completar, validar nova instância (Playwright)

## Média prioridade
- [ ] Containerização app completa (backend + frontend)
  - [ ] Dockerfile/Containerfile para backend
  - [ ] Dockerfile/Containerfile para frontend
  - [ ] Compose/Podman Compose unificado (app + mongo + nginx opcional)
- [ ] Segurança
  - [ ] Externalizar `jwt.secret` via env
  - [ ] CORS: restringir `allowed-origins`
  - [ ] Revisar políticas de senha/erros
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