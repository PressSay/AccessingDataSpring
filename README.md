# AccessingDataSpring

##  **Update configuration**

#### In src/main/java/com/example/accessingdata/resources/application.properties change:

- spring.datasource.url=jdbc:mysql://localhost:3306/**YourDatabase**
- spring.datasource.username=**YourUsername**
- spring.datasource.password=**YourPassword**

#### In src/main/java/com/example/accessingdata/resources/application.yml can change if you want:

```
server:
  port: 8080
```

## **Testing**

### **Run command:**

#### Linux

```
./gradlew test
```

#### Window

```
gradlew.bat test
```

*Note: When you finish testing, you must drop your database to make sure everything is normal before running the bootrun*

## **BootRun**

#### Linux

```
./gradlew bootrun
```

#### Window

```
gradlew.bat bootrun
```

<br />
<br />

# List **api** resources

```
http://localhost:8080/books
http://localhost:8080/authors
http://localhost:8080/addresses
http://localhost:8080/libraries
```

To get the list of books entity, we use the following api:

```
http://localhost:8080/books
```

To get a books entity with **id=1**, we use the following api:

```
http://localhost:8080/books/1
```

To get a dependent entity books in **author with id=1**, we use the following api:

```
http://localhost:8080/authors/1/books
```

To get the address entity from the **library with id=1**, we use the following api:

```
http://localhost:8080/libraries/1/addresss
```

- To better understand the `addresses api` above, go to **src/main/java/com/example/accessingdata/models/library.java**.
- We can completely adjust the **path of address**, see line 28.


<br />
<br />

# Use Curl to ( GET, POST, PUT, DELETE )

- These test commands have been collected in the file: `src/test/java/com/example/accessingdata/AccessingdataApplicationTest.java`

<br />

## One to one relationship

<br />

Use curl to `POST` a new library entity to the server with the attribute **"name"="My Library"**

```
curl -i -X POST -H "Content-Type:application/json" -d '{"name":"My Library"}' http://localhost:8080/libraries
```

<br />

Use curl to `POST` a new address entity to the server

```
curl -i -X POST -H "Content-Type:application/json" -d '{"location":"Main Street nr 5"}' http://localhost:8080/addresses
```

<br />

Use curl to **create an address association** with the library using the `PUT` protocol

```
curl -i -X PUT -d "http://localhost:8080/addresses/1" -H "Content-Type:text/uri-list" http://localhost:8080/libraries/1/address
```

<br />

Use curl `GET` to get the library from **addresses with id=1**

```
curl -i -X GET http://localhost:8080/addresses/1/library
```

<br />

Use curl `DELETE` to delete the address association

```
curl -i -X DELETE http://localhost:8080/libraries/1/addresss
```

<br />

## One to many relationship

<br />

Use curl to `POST` a new book entity to the server with the attribute **"title":"Book1"**

```
curl -i -X POST -d "{\"title\":\"Book1\"}" -H "Content-Type:application/json" http://localhost:8080/books
```

<br />

Use curl to `PUT` **association library** with (name:"My Library" because http://localhost:8080/libraries/1) for entity "Book1" in the command line above

```
curl -i -X PUT -H "Content-Type:text/uri-list" -d "http://localhost:8080/libraries/1" http://localhost:8080/books/1/library
```

<br />

Use curl to `GET` book list from library id=1

```
curl -i -X GET http://localhost:8080/libraries/1/books
```

<br />

Use curl to `DELETE` **association library** with (name:"My Library" because we mapped them above)

```
curl -i -X DELETE http://localhost:8080/books/1/library
```

<br />

## Many to many relationship

<br />

Use curl to `POST` an author entity **"named":"author1"**

```
curl -i -X POST -H "Content-Type:application/json" -d "{\"name\":\"author1\"}" http://localhost:8080/authors
```

<br />

Use curl to `POST` book2

```
curl -i -X POST -H "Content-Type:application/json" -d "{\"title\":\"Book 2\"}" http://localhost:8080/books
```

<br />

Use curl to `PUT` author **association with book1 and book2**

```
curl -i -X PUT -H "Content-Type:text/uri-list" --data-binary @uris.txt http://localhost:8080/authors/1/books
```

- *Note that we need to create a file named `uris.txt` with the following content:*

```
http://localhost:8080/books/1
http://localhost:8080/books/2
```

<br />

Use curl to `GET` book from **author id=1**

```
curl -i -X GET http://localhost:8080/authors/1/books
```

