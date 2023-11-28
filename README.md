# SheepMyBoat

```
                             v  ~.      v
                    v           /|
                               / |          v
                        v     /__|__
                            \--------/
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`~~~~~~'~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```
## Description
SheepMyBoat is a TCP two players game application. It's a twin of the Battleship game.       
The app use the SheepMyBoat protocole, you can find the information about it [here.](/PROTOCOL.md)

## Building the app
The app use maven  so in order to build and package the app use this command.

```sh
./mvnw dependency:resolve clean compile package
```

## Running the app
Download the .jar file into the lastest [release]() and run the command explained under this text. You can also download the source code and build the code with Maven but make sure to have included all dependencies.

### Launch the game server

```sh
java -jar <path-to-jar> server -a <address> -p <port>
```

### Launch a player(client)

```sh
java -jar <path-to-jar> client -a <address> -p <port>
```

### Description

- `-a, --address <outputFile>`: IP address to connect.
- `-p, --port <outputFile>`: Port to connect.
