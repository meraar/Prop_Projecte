DriverCtrlAtributs
En aquest driver es proven totes les funcionalitats de la classe CtrlAtributs.

Hi ha inclos un joc de proves anomenat: input_jproves.txt

Prova totes les funcionalitats de la classe que funcionin correctament i com es esperat detectant els possibles errors:
 - Crea el control d'atributs amb el control de persistencia
 - Genera els atributs a partir del header del fitxer llegit
 - Es demana al usuari que defineixi els tipus dels atributs generats
 - Genera el preproces per cada atribut obtingut del fitxer:
        + Si l'atribut es del tipus String demana tots els valors possibles i els assigna a la llista de valors possibles
        + Si l'atribut es del tipus float demana tots els intervals de l'atribut i se'ls assigna a la llista d'intervals
        + Si l'atribut es de tipus boolean demana el valor considerat fals i el cert i se'ls assigna.

Per executar el driver, utilitzar la comanda: java -jar Driver_CtrlAtributs.jar

Per utilitzar el joc de proves: java -jar Driver_CtrlAtributs.jar < input_jproves.txt
