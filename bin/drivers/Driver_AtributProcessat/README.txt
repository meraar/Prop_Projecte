Driver AtributProcessat
En aquest driver es proven totes les funcionalitats de la classe AtributProcessat.

Hi han inclosos dos jocs de proves que proven coses diferents:

input_jprova1.txt:
Prova totes les funcionalitats de la classe que funcionin correctament i com es esperat:
 - Crea un atribut amb nom atribut1 i valor valor1
 - Consulta el nom amb el getter
 - Consulta el valor amb el getter
 - Canvia el nom del atribut amb el setter
 - Consulta el nou nom
 - Canvia el valor del atribut amb el setter
 - Consulta el nou valor
 - Comprova que la igualtat funciona amb noms i valors diferents
 - Comprova que la igualtat funciona amb noms iguals i valors diferents
 - Comprova que la igualtat funciona amb noms diferents i valors iguals
 - Comprova que la igualtat funciona amb noms i valors iguals


input_jprova2.txt:
Prova totes les funcionalitats de la classe amb valors incorrectes i comprova que es tornin els missatges derror correctes:
 - Crea un atribut amb nom atribut1 i valor valor1
 - Canvia el nom a una string buida: es mostra error perque el nom no pot estar buit
 - Canvia el valor a una string buida: es mostra error perque el valor no pot estar buit


Per executar el driver, utilitzar la comanda: java -jar Driver_AtributProcessat.jar

Per utilitzar el joc de proves 1: java -jar Driver_AtributProcessat.jar < input_jprova1.txt
Per utilitzar el joc de proves 2: java -jar Driver_AtributProcessat.jar < input_jprova2.txt