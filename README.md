[![Build Status](https://travis-ci.org/TALESJULIAN/api-cidades.svg?branch=master)](https://travis-ci.org/TALESJULIAN/api-cidades)
# api-cidades
Api to parse a cvs file of cities 

A empresa XPTO Systems precisa construir um sistema web para leitura e manutenção de uma lista de cidades que hoje está em um arquivo CSV. Como a empresa está antenada com as novas tecnologias ela precisa de um sistema web, pois os usuários irão acessar os dados do sistema via smartphone. A empresa contratou uma agência e a agência decidiu separar o trabalho em duas partes: uma equipe irá fazer a interface e equipe a integração com a base de dados. Para a integração com a base de dados foi sugerida a criação de uma API rest para fazer o controle das cidades cadastradas. Todos o tráfego de dados do serviço deve ser feito no formato JSON. Foi definido que a API terá serviços para atender os seguintes requisitos: 

1. Ler o arquivo CSV das cidades para a base de dados; OK
2. Retornar somente as cidades que são capitais ordenadas por nome; OK
3. Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades; OK
4. Retornar a quantidade de cidades por estado; OK
5. Obter os dados da cidade informando o id do IBGE; OK
6. Retornar o nome das cidades baseado em um estado selecionado; OK
7. Permitir adicionar uma nova Cidade; OK
8. Permitir deletar uma cidade; OK
9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. Retornar assim todos os objetos que contenham tal string; 
10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais; OK
11. Retornar a quantidade de registros total; OK
12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta); OK

O código fonte deve ser comitado no GitHub, por favor nos passe sua conta para acompanharmos. 