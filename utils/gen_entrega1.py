import glob
import os
from shutil import copyfile, rmtree
from distutils.dir_util import copy_tree

binFolder = os.getcwd() + '/src/bin'
exeFolder = os.getcwd() + '/bin/drivers'
srcFolder = os.getcwd() + '/src'
testsFolder = os.getcwd() + '/tests'
libFolder = os.getcwd() + '/lib'
docsFolder = os.getcwd() + '/docs'

driverOutFolder = os.getcwd() + '/bin/drivers'
finalFolder = os.getcwd() + '/entregues'

manifestFile = os.getcwd() + '/manifest.txt'

driverFolders = [binFolder + '/Drivers', binFolder + '/Drivers/Domini']


def getMainClass(filename):
    filename = filename.replace(binFolder + '/', '')
    filename = filename.replace('.class', '')

    return filename.replace('/', '.')


def generateJar(file):
    filename = os.path.basename(file)
    driver = filename.replace('.class', '')

    manifest = os.getcwd() + '/manifest-' + filename + '.txt'

    # Copy manifest template
    copyfile(manifestFile, manifest)

    # Append main class in manifest
    with open(manifest, "a") as myfile:
        myfile.write("Main-Class: " + getMainClass(file) + "\n")

    outFolder = driverOutFolder + '/%s' % driver
    outFile = outFolder + '/%s.jar' % driver

    if not os.path.isdir(outFolder):
        os.makedirs(outFolder)

    # Generate jar file
    os.system('cd %s; jar cvfm %s %s ./**/*.class ./**/**/*.class' % (binFolder, outFile, manifest))

    # Remove temp manifest
    os.remove(manifest)


# Run makefile to generate binaries and JavaDoc
os.system('cd %s; make classes javadoc' % srcFolder)

# Generate JAR executables
for folder in driverFolders:
    # Generate driver JAR files
    for file in glob.glob(folder + "/Driver*.class"):
        generateJar(file)

# Create tmp folder
tmpFolder = finalFolder + '/tmp'

if not os.path.isdir(tmpFolder):
    os.makedirs(tmpFolder)

# Copy docs and rename to DOCS
copy_tree(docsFolder, tmpFolder + '/DOCS')

# Clean binaries and JavaDoc
os.system('cd %s; make clean' % srcFolder)

# Create FONTS folder
os.makedirs(tmpFolder + '/FONTS')

# Copy sources and tests
copy_tree(srcFolder, tmpFolder + '/FONTS/src')
copy_tree(testsFolder, tmpFolder + '/FONTS/tests')
copy_tree(libFolder, tmpFolder + '/FONTS/lib')
copyfile("index_fonts.txt", tmpFolder + '/FONTS/index.txt')

# Copy executables and rename to EXE
copy_tree(exeFolder, tmpFolder + '/EXE')
copyfile("index_exe.txt", tmpFolder + '/EXE/index.txt')

# Copy index.txt and descripcio_grup.txt
copyfile("descripcio_grup.txt", tmpFolder + '/descripcio_grup.txt')

if not os.path.isdir(finalFolder):
    os.makedirs(finalFolder)

# Zip folder
os.system('cd %s; zip -r %s/entrega1_subgrup1-2.zip *' % (tmpFolder, finalFolder))

# Remove tmp folder
rmtree(tmpFolder)
