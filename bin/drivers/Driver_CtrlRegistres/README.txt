Driver CtrlRegistres
En aquest driver es proven totes les funcionalitats de la classe CtrlRegistres.

Hi han inclosos dos jocs de proves que proven coses diferents:

input_jprova1.txt:
Prova totes les funcionalitats de la classe que funcionin correctament i com es esperat:
 - Processa els atributs del fitxer data.csv inclos dins la carpeta
 - Conulta els registres processats
 - Exporta els registres a un fitxer test.registres.prop
 - Buida el conjunt de registres
 - Importa els registres del fitxer test.registres.prop
 - Consulta els registres processats

input_jprova2.txt:
Prova totes les funcionalitats de la classe amb valors incorrectes i comprova que es tornin els missatges derror correctes:
 - Processa els atributs d'un fitxer inexistent: torna un error perque el fitxer no existeix
 - Importa els registres d'un fitxer inexistent: torna un error perque el fitxer no existeix

Per executar el driver, utilitzar la comanda: java -jar Driver_CtrlRegistres.jar

Per utilitzar el joc de proves 1: java -jar Driver_CtrlRegistres.jar < input_jprova1.txt
Per utilitzar el joc de proves 2: java -jar Driver_CtrlRegistres.jar < input_jprova2.txt