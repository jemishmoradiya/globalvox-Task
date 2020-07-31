**1. Tasks**

```bash
Create a spring boot project which can do following:

1. A rest API that accepts an Image file and stores in the in memory db
2. A get API which prints the same image on browser
3. A rest APi that converts given integer into binary

- e.g. 12 would convert in 1100 write your own logic to convert into binary - no third party APIs)
```
 

**2. Run the app using maven**

```bash
cd globalvox-Task
mvn spring-boot:run
```

**3. How to access**

```bash
1. The application can be accessed at `http://localhost:8080`.
2. Swagger can be accessed at http://localhost:8080/swagger-ui.html
3. In memory db can be accessed at http://localhost:8080/h2/
```

**3. Rest API testing**

```bash
Postman or swagger ui both can be used

1. /api/uploadFile (POST)
2. /api/view-file/{fileId} (GET)
3. /api/int-to-binary/{intValue} (GET)
```
Use Swagger ui for batter testing.


