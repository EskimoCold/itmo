#!bin/bash

javac -cp ".:../lib/Pokemon.jar" -d classes/ ../src/ru/ifmo/se/pokemon/*
jar cvmf MANIFEST.MF lab1.jar -C classes/ .
