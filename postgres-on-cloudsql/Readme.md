# Cloud SQL CRUD
## Description
Remote Database access on your browser.

<<<<<<< HEAD
## Description
write your connection name at 'src/main/resources/application.properties'.

## Prepare
=======
## Prepare
1. Create database and table on google cloud.
    ```bash
    # test table
    CREATE TABLE kenko_record
    (id char(4) not null,
    name text not null,
    food text not null,
    PRIMARY KEY(id));
    ```

1. Write your connection name at 'resources/application.properties'.


### CRUD oparate with cURL
Command prompt samples.
>>>>>>> refs/remotes/origin/main
```bash
# All records
curl -X GET "localhost:8080/"

<<<<<<< HEAD
### CRUD oparate with cUrl
Browsing 'localhost:8080/' view all records.

use cURL command (ex Command prompt)
```bash
=======
>>>>>>> refs/remotes/origin/main
# CREATE
curl -X POST -H "Content-Type: application/json" -d @data.json localhost:8080/crud
# READ
curl -X GET "localhost:8080/crud?id=100"
# UPDATE
curl -X PUT -H "Content-Type: application/json" -d @data.json localhost:8080/crud
# DELETE
curl -X DELETE -d "id=100" localhost:8080/crud
```

data.json is...
```bash
{
  "id":100,
  "name":"沖縄",
  "food":"ソーキそば2"
}
```
