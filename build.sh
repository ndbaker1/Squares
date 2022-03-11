#!/bin/sh
# run this from the root directory
CLASS_DIR="build"
JAR_NAME="Squares.jar"

javac **/*.java -d $CLASS_DIR
cd $CLASS_DIR
jar cvfe $JAR_NAME Game *
mv $JAR_NAME ../$JAR_NAME
cd ..
java -jar Squares.jar