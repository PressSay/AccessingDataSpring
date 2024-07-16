# AccessingDataSpring

##  **Update configuration**

#### In src/main/java/com/example/accessingdata/resources/application.properties change:

- spring.datasource.url=jdbc:mysql://localhost:3306/**YourDatabase**
- spring.datasource.username=**YourUsername**
- spring.datasource.password=**YourPassword**

#### In src/main/java/com/exmaple/accessingdata/resources/application.yml can change if you want:

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
gradlew.bat bottrun
```
