## Server
Start the server : `start -i <ip> -p <port>`

## Client
#### Connection
Command: `connect -i <ip> -p <port>`

|Response|Detail|
| ---- | ---- |
|`Connected`|The client is connected!|
|`Error_1`|No server is available on this IP or port.|
|`Error_2`|There is too much people on the server.|

#### Register username
Command: `username <username>`

|Response|Detail|
| ---- | ---- |
|`Accepted`|The username as been added.|
|`Error_3`|The username is too long sorry.|

#### Start game
Command: `start`

|Response|Detail|
| ---- | ---- |
|`Start`|The game start!|
|`Wait`|The other player is not ready yet.|
|`Error_4`|You need to have a username.|

#### Place boat (actually random)

#### Attack sheep
Command: `Shoot <position -> B1>`

|Response|Detail|
| ---- | ---- |
|`Touched`|You touched a sheep.|
|`Missed`|You missed the shot.|
|`Sinked`|You sink a boat.|

#### Finish game

|Response|Detail|
| ---- | ---- |
|`End`|The game is finished|

Command : `Replay`

|Response|Detail|
| ---- | ---- |
|`Accepeted`|Your oppenent accepted the challenge.|
|`Denied`|Your oppenent deserted.|
