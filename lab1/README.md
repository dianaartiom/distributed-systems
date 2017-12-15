###For the first laboratory work I've created an application that does the following:

#### Receive messages
The received messaes are corespond to the data structure that looks like this:
```
message:
	routingKey
	queueName
	command
	payload
	response
```

**routing key** - directs the message to some Queue, which meets the regex <br />
**queueName** - direcs the message to the Queue with the specified name<br />
**command** - an int that tells the message what is to be used for(e.g. create queue, delete, etc.) <br />
**payload** - "what" do you want to send <br />
**response** - what did the broker do with the message<br />

#### The application works as follows:
1. The message broker runs continuously and accepts clients to connect
2. Cliets connect. The connection is handled asynchronously.

