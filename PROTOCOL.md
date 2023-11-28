# SheepMyBoat application's protocol

## 1 - Overview
The SheepMyBoat protocol is meant to play a battleship game over the network. It's a client-server protocol.   
The client connects to a server and request to play a new game if another player is also ready to play.    
The server manage the game's logic between the two players.   

## 2 - Transport protocol
The SheepMyBoat protocol uses the `TCP protocol`. The server runs on the `port 11111`.  
The client has to know the IP address of the server to connect to. **The client establishes the connection with the server.**   
The connection ends when a game is over. If you want to replay or play with another player you will have to reconnect.

## 3 - Messages

### Server
Start the server : `server -i <ip (default -> 127.0.0.1)> -p <port (default -> 11111)>`

### Client
#### Connection
Command: `client -i <ip> -p <port (default -> 11111)>`
|Response|Detail|
| ---- | ---- |
|`Connected`|The client is connected!|
|`Error_1`|There is too much people on the server.|

#### Register username
Command: `username <username>`
|Response|Detail|
| ---- | ---- |
|`Accepted`|The username as been added.|
|`Error_2`|The username is too long sorry.|

#### Start game
Command: `start`
|Response|Detail|
| ---- | ---- |
|`Error_3`|You need to have a username.|

#### Attack sheep
Command: `shoot <position -> B1>`
|Response|Detail|
| ---- | ---- |
|`Touched`|You touched a sheep.|
|`Missed`|You missed the shot.|
|`Sinked`|You sink a boat.|
|`Error_4`|Shoot out of range|

#### Finish game
|Response|Detail|
| ---- | ---- |
|`EndGame`|The game is finished|
