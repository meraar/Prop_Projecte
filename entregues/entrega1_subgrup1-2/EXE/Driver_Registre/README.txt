Driver Registre
En aquest driver es proven totes les funcionalitats de la classe Registre.

Hi han inclosos dos jocs de proves que proven coses diferents:

input_jprova1.txt:
Prova totes les funcionalitats de la classe que funcionin correctament i com es esperat:
 - Especifica el identificador del registre
 - Consulta el identificador del registre
 - Afegeix 3 atributs al registre
 - Consulta el llistat datributs dins del registre
 - Modifica el valor dun atribut
 - Consulta el llistat datributs per veure el valor modificat
 - Consulta si existeix un atribut que si que existeix
 - Elimina un atribut
 - Consulta si existeix latribut eliminat
 - Comprova si existeix un conjunt datributs dins del registre (si que existeix)
 - Comprova si existeix un conjunt datributs dins del registre (no existeix)

input_jprova2.txt:
Prova totes les funcionalitats de la classe amb valors incorrectes i comprova que es tornin els missatges derror correctes:
 - Afegeix 3 atributs al registre
 - Afegeix un atribut ja existent: es mostra error perque latribut ja existeix
 - Modifica un atribut inexistent: es mostra error perque latribut no existeix
 - Elimina un atribut inexistent: es mostra error perque latribut no existeix

Per executar el driver, utilitzar la comanda: java -jar Driver_Registre.jar

Per utilitzar el joc de proves 1: java -jar Driver_Registre.jar < input_jprova1.txt
Per utilitzar el joc de proves 2: java -jar Driver_Registre.jar < input_jprova2.txt