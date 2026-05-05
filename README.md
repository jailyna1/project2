## Getting Started

TO RUN FOLLOW THESE INSTRUCTIONS (from project2 folder):
1. Terminal 1 - run:
    - apache-maven-3.9.14\bin\mvn.cmd clean compile
    - apache-maven-3.9.14\bin\mvn.cmd exec:java -D"exec.mainClass=com.restaurant.server.RestaurantGrpcServer"
2. Terminal 2 - run: 
    - apache-maven-3.9.14\bin\mvn.cmd exec:java -D"exec.mainClass=com.restaurant.client.RestaurantGrpcClient"

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `data`: the folder to maintain hard copies of data

Meanwhile, the compiled output files will be generated in the `bin` folder by default.


## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
