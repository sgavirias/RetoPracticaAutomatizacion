Feature: realizar busqueda de noticia en El Colombiano
  Como usuario no registrado
  Quiero buscar noticias usando filtros
  Para tomar evidencia de ellas

Scenario: realizar la busqueda de dos noticias
  Given ingreso a la pagina el colombiano caso "001"
  When  selecciono las noticias
  Then  tomo las evidencias de ellas

