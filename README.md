hangmanz_server
===============

The Hangmanz Server Engine (Java EE/RESTful) for the Hangman game.


### ## How to test the code


###### Database
Setup a mongoDB instance and assure it’s running. 

If the location (IP) and port are not the default values (localhost:27017), please, update the Database Connection Data located on the Java class com.jjorgemoura.hangmanz.db.MongoDBManager for the valid values. The vars to be updated are MONGODB_IP for the IP and MONGODB_PORT for the port.


##### Server
The Server component is a Java EE application. The Application Server (AS) chosen was the Glassfish Application Server. The Glassfish is included in the Netbeans bundle.

The project is a Maven project. In the POM file was added the mongoDB dependency.

The RESTful web services were implemented respecting the official JAX-RS API, being the implementation being made with the Jersey framework. The POM file does’t include any reference to Jersey because the Jersey are already included in the Glassfish.

