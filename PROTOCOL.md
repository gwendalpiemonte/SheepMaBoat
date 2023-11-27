## Server
Start the server : `server -i <ip (default -> 127.0.0.1)> -p <port (default -> 11111)>`

## Client
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

#### Place boat (actually random)

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
