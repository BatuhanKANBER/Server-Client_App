# MULTIPLE CLIENT TO SINGLE SERVER APPLICATION WITH JAVA

We start our project by creating Client, ClientHandler and Server Classes.
We call the ServerSocket object in the Server Class and enter the port number in its parameter.
Then, we call the socket object and access this socket object to the server we created with the accept() method.
Then we call the ClientHandler class and give the socket to its parameter.
Then, we give the created clientHandler object the start() method and thus provide multiple client access to the server side.

