## Running_Late Maven project Instructions

###### In your platform terminal, powershell, or IDE terminal input the following commands:

```
mvn clean install
 ```
###### When first building the project.
```
mvn clean compile
```

###### To properly compile the source files

```
mvn test
```

###### To run the tests associated with the game project 

```
mvn clean compile assembly:single 
```

###### To compile all sources into a single executable jar file (the game executable file itself).
###### You will find the executable file under the "Game/running_late/target" folder.

###### DISCLAIMER Make sure to build, run and test the game program (using standard java running practices) in the same directory containing the "pom.xml" file
