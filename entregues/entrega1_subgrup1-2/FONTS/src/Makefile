CLASSES = prop
DRIVERS = Drivers

BIN_FOLDER = bin
JAVADOC_FOLDER = ../docs/javadoc

classes:
	javac -d $(BIN_FOLDER) $(CLASSES)/*.java $(CLASSES)/Domini/*.java $(DRIVERS)/*.java

javadoc:
	javadoc -sourcepath . prop/**/*.java -d $(JAVADOC_FOLDER) -encoding utf8 -docencoding utf8 -charset utf8

all: classes

clean:
	rm -rf $(BIN_FOLDER) $(JAVADOC_FOLDER)