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

# Danh sách **api** resources

```
http://localhost:8080/books
http://localhost:8080/authors
http://localhost:8080/addresses
http://localhost:8080/libraries
```

### Muốn lấy danh sách thực thể books ta sử dụng api sau:

```
http://localhost:8080/books
```

### Muốn lấy một thực thể books có id=1 ta sử dụng api sau:

```
http://localhost:8080/books/1
```

### Muốn lấy một thực thể phụ thuộc books trong author có id=1 ta sử dụng api sau:

```
http://localhost:8080/authors/1/books
```

### Muốn lấy thực thể address từ library có id=1 ta sử dụng api sau:

```
http://localhost:8080/libraries/1/addresss
```

- Để hiểu hơn về **api của adress** bên trên hãy vào **src/main/java/com/example/accessingdata/models/library.java**.
- Ta hoàn toàn có thể điều chỉnh path address hãy xem dòng 28.


<br />
<br />

# Sử dụng Curl để get post put

- Những câu lệnh test này đã được gom gọn trong file: **src/test/java/com/example/accessingdata/AccessingdataApplicationTest.java**

<br />

## Quan hệ one to one

<br />

### *Sử dụng curl để post một thực thể library mới lên server có thuộc tính name="My Library"*

```
curl -i -X POST -H "Content-Type:application/json" -d '{"name":"My Library"}' http://localhost:8080/libraries
```

<br />

### *Sử dụng curl để **post** một thực thể address mới lên server*

```
curl -i -X POST -H "Content-Type:application/json" -d '{"location":"Main Street nr 5"}' http://localhost:8080/addresses
```

<br />

### *Sử dụng curl để **tạo association address** với library bằng giao thức **PUT***

```
curl -i -X PUT -d "http://localhost:8080/addresses/1" -H "Content-Type:text/uri-list" http://localhost:8080/libraries/1/address
```

<br />

### *Sử dụng curl **get** để lấy library từ addresses có id là 1*

```
curl -i -X GET http://localhost:8080/addresses/1/library
```

<br />

### *Sử dụng curl **delete** để xóa address association*

```
curl -i -X DELETE http://localhost:8080/libraries/1/addresss
```

<br />

## Quan hệ one to many

<br />

### *Sử dụng curl để **post** một thực thể book mới lên server có thuộc tính title:"Book1"*

```
curl -i -X POST -d "{\"title\":\"Book1\"}" -H "Content-Type:application/json" http://localhost:8080/books
```

<br />

### *Sử dụng curl để **put association library** có (name:"My Library" vì http://localhost:8080/libraries/1) cho thực thể "Book1" ở dòng lệnh phía trên*

```
curl -i -X PUT -H "Content-Type:text/uri-list" -d "http://localhost:8080/libraries/1" http://localhost:8080/books/1/library
```

<br />

### *Sử dụng curl để **Get** danh sách book từ library id 1*

```
curl -i -X GET http://localhost:8080/libraries/1/books
```

<br />

### *Sử dụng curl để **delete association library** có (name:"My Library" vì ta đã ánh xạ chúng ở phía trên)*

```
curl -i -X DELETE http://localhost:8080/books/1/library
```

<br />

## Quan hệ many to many

<br />

### *Sử dụng curl để **post** một thực thể author có name:"author1"*

```
curl -i -X POST -H "Content-Type:application/json" -d "{\"name\":\"author1\"}" http://localhost:8080/authors
```

<br />

### *Sử dụng curl để **post** book2*

```
curl -i -X POST -H "Content-Type:application/json" -d "{\"title\":\"Book 2\"}" http://localhost:8080/books
```

<br />

### *Sử dụng curl để **put** association author với book1 và book2*

```
curl -i -X PUT -H "Content-Type:text/uri-list" --data-binary @uris.txt http://localhost:8080/authors/1/books
```

- *Chú ý ta cần tạo một file có tên **uris.txt** với nội dung sau:*

```
http://localhost:8080/books/1
http://localhost:8080/books/2
```

<br />

### *Sử dụng curl để **get** book từ author id=1*

```
curl -i -X GET http://localhost:8080/authors/1/books
```

