Driver CjtRegistres
En aquest driver es proven totes les funcionalitats de la classe CjtRegistres.

Hi han inclosos dos jocs de proves que proven coses diferents:

input_jprova1.txt:
Prova totes les funcionalitats de la classe que funcionin correctament i com es esperat:
 - Crea un conjunt amb 3 registres utilitzant el setter
 - Consulta el conjunt resultant
 - Elimina un registre del conjunt
 - Consulta el conjunt amb els nous valors
 - Afegeix un nou registre al conjunt
 - Consulta el conjunt amb els nous valors
 - Modifica el primer registre
 - Consulta el registre modificat
 - Consulta el set datributs unics del conjunt
 - Consulta els atributs del conjunt ordenats
 - Calcula el suport dun conjunt datributs pels atributs que conte el CjtRegistres


input_jprova2.txt:
Prova totes les funcionalitats de la classe amb valors incorrectes i comprova que es tornin els missatges derror correctes:
 - Crea un conjunt amb 3 registres utilitzant el setter
 - Consulta el conjunt resultant
 - Consulta un registre inexistent: es mostra error perque el registre no existeix
 - Elimina un registre inexistent: es mostra error perque el registre no existeix
 - Actualitza un registre inexistent: es mostra error perque el registre no existeix
 - Afegeix un registre amb una ID ja existent: es mostra error perque ja existeix un registre amb aquesta ID


Per executar el driver, utilitzar la comanda: java -jar Driver_CjtRegistres.jar

Per utilitzar el joc de proves 1: java -jar Driver_CjtRegistres.jar < input_jprova1.txt
Per utilitzar el joc de proves 2: java -jar Driver_CjtRegistres.jar < input_jprova2.txt