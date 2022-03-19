Driver CtrlRegles
En aquest driver es proven totes les funcionalitats de la classe CtrlRegles.

Hi ha inclosos dos jocs de proves anomenats:

input_jprova1.txt:
Prova totes les funcionalitats de la classe que funcionin correctament i com es esperat detectant els possibles errors:
 - Crea la constructora de la controladora de les regles
 - Genera les regles d'associacio
 - Valida les regles d'associacio
 - Exporta les regles d'associacio a un arxiu binari
 - Importa les regles d'associacio

input_jprova2.txt:
Prova les funcionalitats quan no introdueixes be un valor:
 - Crea la constructora de la controladora de les regles
 - Intenta generar les regles pero el suport te un valor invalid
 - Intenta validar les regles pero al no haver generat abans les regles et demana que les generis de nou

Per executar el driver, utilitzar la comanda: java -jar Driver_CtrlRegles.jar

Per utilitzar el joc de proves 1: java -jar Driver_CtrlRegles.jar < input_jprova1.txt
Per utilitzar el joc de proves 2: java -jar Driver_CtrlRegles.jar < input_jprova2.txt