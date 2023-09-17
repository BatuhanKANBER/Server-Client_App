# MULTIPLE CLIENT TO SINGLE SERVER APPLICATION WITH JAVA

We start our project by creating Client, ClientHandler and Server Classes.
We call the ServerSocket object in the Server Class and enter the port number in its parameter.
Then, we call the socket object and access this socket object to the server we created with the accept() method.
Then we call the ClientHandler class and give the socket to its parameter.
Then, we give the created clientHandler object the start() method and thus provide multiple client access to the server side.

![](images/1.png)

We get the address information of our client by using the InetAddress object in our Client Class.
Then, we perform input and output operations using BufferedReader and PrintWriter objects.
Then, we write the function that enables the use of Windows commands using the Process object.
And we complete our Client class.

![](images/2.png)
