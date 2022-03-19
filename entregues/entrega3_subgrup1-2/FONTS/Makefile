CLASSES = prop

BIN_FOLDER = bin
JAVADOC_FOLDER = ../docs/javadoc

classes:
	javac -d $(BIN_FOLDER) $(CLASSES)/*.java $(CLASSES)/Domini/*.java $(CLASSES)/Persistencia/*.java $(CLASSES)/Presentacio/*.java $(CLASSES)/Presentacio/utils/table/*.java

javadoc:
	javadoc -sourcepath . prop/**/*.java prop/Presentacio/utils/table/*.java -d $(JAVADOC_FOLDER) -encoding utf8 -docencoding utf8 -charset utf8 -author

all: classes

clean:
	rm -rf $(BIN_FOLDER) $(JAVADOC_FOLDER)