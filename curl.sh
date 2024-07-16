# Quan hệ one to one

# Sử dụng curl để post một thực thể library mới lên server có thuộc tính name="My Library"
curl -i -X POST -H "Content-Type:application/json" -d '{"name":"My Library"}' http://localhost:8080/libraries

# Sử dụng curl để post một thực thể address mới lên server
curl -i -X POST -H "Content-Type:application/json" -d '{"location":"Main Street nr 5"}' http://localhost:8080/addresses

# Sử dụng curl để tạo association address với library bằng giao thức PUT
curl -i -X PUT -d "http://localhost:8080/addresses/1" -H "Content-Type:text/uri-list" http://localhost:8080/libraries/1/address

# Sử dụng curl get để lấy library từ addresses có id là 1
curl -i -X GET http://localhost:8080/addresses/1/library

# Sử dụng curl delete để xóa address association
curl -i -X DELETE http://localhost:8080/libraries/1/addresss

# Quan hệ one to many

# Sử dụng curl để post một thực thể book mới lên server có thuộc tính title:"Book1"
curl -i -X POST -d "{\"title\":\"Book1\"}" -H "Content-Type:application/json" http://localhost:8080/books

# Sử dụng curl để put association library có (name:"My Library" vì http://localhost:8080/libraries/1)
# cho thực thể "Book1" ở dòng lệnh phía trên
curl -i -X PUT -H "Content-Type:text/uri-list" -d "http://localhost:8080/libraries/1" http://localhost:8080/books/1/library

# Sử dụng curl để Get danh sách book từ library id 1
curl -i -X GET http://localhost:8080/libraries/1/books

# Sử dụng curl để delete association library có (name:"My Library" vì ta đã ánh xạ chúng ở phía trên)
curl -i -X DELETE http://localhost:8080/books/1/library

# Quan hệ many to many

# Sử dụng curl để post một thực thể author có name:"author1"
curl -i -X POST -H "Content-Type:application/json" -d "{\"name\":\"author1\"}" http://localhost:8080/authors

# Sử dụng curl để book2
curl -i -X POST -H "Content-Type:application/json" -d "{\"title\":\"Book 2\"}" http://localhost:8080/books

# Sử dụng curl để tạo association author với book1 và book2
curl -i -X PUT -H "Content-Type:text/uri-list" --data-binary @uris.txt http://localhost:8080/authors/1/books

# Chú ý ta cần tạo một file có tên uris.txt với nội dung sau:
# http://localhost:8080/books/1
# http://localhost:8080/books/2

# Sử dụng curl để get book từ author id=1
curl -i -X GET http://localhost:8080/authors/1/books