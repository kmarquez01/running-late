# Running Late

This game was created for a project in a computing programming class (CMPT 276). Essentially the premise is that a kid forgot his homework on campus. Unfortunately it is past closing hours, so he snuck in and found them scattered throughout the floor. As he was about to leave he noticed that there are security guards patrolling to make sure no one is around! His goal is to collect the papers scattered throughout campus and leave without getting caught.


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
###### You will find the executable file under the "Game/running_late/target" folder labelled as "Running_late_jar_with_dependencies.jar".

###### DISCLAIMER Make sure to build, run and test the game program (using standard java running practices) in the same directory containing the "pom.xml" file
