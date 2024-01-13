mkdir build

javac -d build -cp ~/Pokemon.jar -sourcepath src LabTwo/src/Main.java LabTwo/src/Moves/Physical/*.java LabTwo/src/Moves/Special/*.java LabTwo/src/Moves/Status/*.java LabTwo/src/Pokemons/*

cp ~/Pokemon.jar ~/build

cd build

touch MANIFEST.MF

chmod u+w MANIFEST.MF

echo "Main-Class: Main" > MANIFEST.MF

echo "Class-Path: Pokemon.jar" >> MANIFEST.MF

jar cvmf MANIFEST.MF  lab2.jar * Pokemons/* Moves/*

java -jar lab.jar