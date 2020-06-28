# 
### API CORONA VIRUS <br><br>
### API UTILIZADA: https://covid19-brazil-api.now.sh/
### Versão(rota 'status'): https://covid19-brazil-api.now.sh/api/status/v1

### Essa API utiliza de rotas em ingles na rota 'report/v1/' para trazer os resultados dos paises:

### Todos estados Brasileiros: https://covid19-brazil-api.now.sh/api/report/v1

### Pais Especifico: https://covid19-brazil-api.now.sh/api/report/v1/japan
### Todos: https://covid19-brazil-api.now.sh/api/report/v1/countries

### E Utiliza ROTAS de UF's em portugues para listar os estados do Brasil:
### https://covid19-brazil-api.now.sh/api/report/v1/brazil/uf/sp


### Utiliza DATAS para Consulta especificas:
### https://covid19-brazil-api.now.sh/api/report/v1/brazil/20200530 <- Data em formato americano sem caracteres

# Métodos:

| METHOD | URL | RESPONSE | OBS |
| ------ | --- | -------- | --- |
| `GET` | `/api/status/v1` | `JSON` | Retorna a versão da API |
| `GET` | `/api/report/v1` | `JSON` | Retorna Todos os Estados Brasileiros
| `GET` | `/api/report/v1/countries` | `JSON` | Retorna um JSon com informações de todos os Países |
| `GET` | `/api/report/v1/japan` | `JSON` | Retorna um Pais especifico, apartir da rota **/v1/ColoqueSeuPaisAqui** |
| `GET` | `/api/report/v1/brazil/uf/sp` | `JSON` | Retorna um ESTADO do BRASIL Especivido, apartir da rota **/uf/ColoqueSeuEstadoAqui** |
| `GET` | `/api/report/v1/brazil/20200530` | `JSON` | Retorna as informações do **Brasil**, listando por uma data especifica, sem caracteres **20200510** |
| `GET` | `ERRADA` | `NADA` | Caso alguma consulta esteja errada, ou não exita dados relacionados a essa informação, a API Ira retornar `{"data":[]}` |
# 