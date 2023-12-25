javac -d classes/ ../src/neznayka/enums/*.java ../src/neznayka/interfaces/*.java ../src/neznayka/*.java ../src/*.java
jar cvmf MANIFEST.MF lab.jar -C classes/ .
