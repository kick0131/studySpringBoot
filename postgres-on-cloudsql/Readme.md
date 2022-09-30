# Cloud SQL CRUD
## Description
Remote Database access on your browser.

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
```bash
# All records
curl -X GET "localhost:8080/"

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
