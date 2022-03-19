# Projecte PROP 2019-2020Q2
Subgrup 1.2

Integrants del grup:
 * Carles Capilla Canovas
 * Albert Coma Coma
 * Pere Grau Molina
 * Muhammad Meraj Arshad
 
 
## Compilació de classes a Windows
Si no us funciona el Makefile, haureu de compilar directament des de terminal.  
Aquí, amb "carpeta del projecte" em refereixo a la carpeta base `subgrup1.2`:

Creeu la carpeta `bin/out/` dins la carpeta del projecte. 

La compilació d'arxius s'ha de fer
dins de la carpeta src. Obriu una terminal (el intelliJ té una terminal al menú inferior) i:
```bash
cd src
javac -d ../bin/out prop/*.java Drivers/*.java Drivers/Domini/*.java
```

Se us generaran els fitxers `.class` dins la carpeta `bin/out/` del projecte.

Per executar els drivers, entreu a la carpeta `bin/out` (suposant que s'està a la carpeta base):
```bash
cd bin/out
```

i executeu la classe amb la comanda java i el dom de la classe, especificant les carpetes separades per punts, és a dir,
per exemple, pel driver `Driver_Registre` que es troba dins la carpeta `Drivers/Domini` seria:
```bash
java Drivers.Driver_Registre
```

Per canviar l'input i l'output (és a dir, els jocs de prova), s'utilitzen els modificadors de input i output `<` i `>`, 
respectivament:

```bash
java Drivers.Driver_Registre < path/al/fitxer/input.txt > path/al/fitxer/output.txt
```
 
## Compilació de classes a Linux
Hi ha un arxiu `Makefile` dins la carpeta `src/` del projecte, que conté totes les comandes necessàries per compilar
l'aplicació.

Per compilar, entrar a la carpeta i utilitzar la comanda `make`:

```bash
cd src/
make classes
```

Es generarà una carpeta `bin/` dins la carpeta base del projecte.

Per executar un driver, s'ha d'estar dins la carpeta `bin/out/` i utilitzar la comanda `java` amb el nom de la classe a 
executar com a paràmetre (el nom ha de tenir el package perquè funcioni). Exemple per la classe **Driver_Registre**:
```bash
cd bin/out
java Drivers.Driver_Registre
```

## Generació dels JavaDoc
Igual que amb la compilació de les classes, s'utilitzarà el mateix `Makefile` per a generar tots els fitxers del JavaDoc:

```bash
cd src/
make javadoc
```

Es crearan tots els arxius del javadoc dins la carpeta `docs/javadoc/` del projecte.

## Generació del fitxer entrega
Es pot generar automàticament la carpeta zip a entregar mitjançant els scripts que es troben a la carpeta `utils/`.
S'utilitza `python3` per els scripts generadors.

### Generació entrega 1
Des de la carpeta root del projecte, executar:
```bash
python3 utils/gen_entrega1.py
```

Es generarà el fitxer entregable en format zip dins la carpeta `entregues/` del projecte.