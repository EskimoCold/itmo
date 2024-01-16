javac -d classes/ ../src/lab3/enums/*.java
javac -cp ".:../src" -d classes/ ../src/lab3/interfaces/*.java
javac -cp ".:../src" -d classes/ ../src/lab3/items/*.java
javac -cp ".:../src" -d classes/ ../src/lab3/fight/*.java
javac -cp ".:../src" -d classes/ ../src/lab3/cords/*.java
javac -cp ".:../src" -d classes/ ../src/lab3/exceptions/*.java
javac -cp ".:../src" -d classes/ ../src/lab3/*.java
javac -cp ".:../src" -d classes/ ../src/*.java

jar cvmf MANIFEST.MF lab.jar -C classes/ .
