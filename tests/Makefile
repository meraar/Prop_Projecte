CLASSES = ../src/prop
DRIVERS = ../src/Drivers
TESTS = prop/Domini
LIBS = ../lib

BIN_FOLDER = bin

classes:
	javac -d $(BIN_FOLDER) -cp .:$(LIBS)/hamcrest.jar -cp .:$(LIBS)/junit.jar $(CLASSES)/*.java $(CLASSES)/Domini/*.java $(CLASSES)/Persistencia/*.java $(DRIVERS)/*.java $(TESTS)/*.java

all: classes

clean:
	rm -rf $(BIN_FOLDER) $(JAVADOC_FOLDER)