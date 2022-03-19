DriverInterval
En aquest driver es proven totes les funcionalitats de la classe Interval.

Hi ha inclos un joc de proves anomenat: input_jproves.txt

Prova totes les funcionalitats de la classe que funcionin correctament i com es esperat detectant els possibles errors:
 - Crea un interval buit.
 - Crea un interval amb nom AprovatPelat per comprobar que un interval d'un valor possible ha d'esser totalment tancat
 - Crea un interval amb nom Aprovat de 5 a 10 ambdos inclosos.
 - Crea un interval amb nom Suspes de 0 a 5 sense incloure el 5
 - Crea un interval anomenat limitsMalIndicats amb limit inferior mes gran que limit superior per comprovar que canvia l'assignacio
 - Consulta si un valor que no hauria d'estar dins l'interval hi es
 - Consulta si un valor que hauria de ser dins l'interval hi es
 - Comprova que funcionin tots els setters de limits i nom.

Per executar el driver, utilitzar la comanda: java -jar Driver_Interval.jar

Per utilitzar el joc de proves: java -jar Driver_Interval.jar < input_jproves.txt
