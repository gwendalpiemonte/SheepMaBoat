## Server
Start the server : `start -i <ip> -p <port default->11111>`

## Client
#### Connection
Command: `connect -i <ip> -p <port default->11111>`

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
|`Error_3`|The username is already taken (by the oppenent).|

#### Start game
Command: `start`

|Response|Detail|
| ---- | ---- |
|`Start`|The game start!|
|`Wait`|The other player is not ready yet.|
|`Error_4`|You need to have a username.|

#### Place boat (actually random)

#### Attack sheep
Command: `shoot <position -> B1>`

|Response|Detail|
| ---- | ---- |
|`Touched`|You touched a sheep.|
|`Missed`|You missed the shot.|
|`Sinked`|You sink a boat.|
|`Error_5`|Shoot out of range|

#### Finish game

|Response|Detail|
| ---- | ---- |
|`End <winner>`|The game is finished|

Command : `replay`

|Response|Detail|
| ---- | ---- |
|`Accepeted`|Your oppenent accepted the challenge.|
|`Wait`|Your oppenent is choosing.|
|`Denied`|Your oppenent deserted.|
