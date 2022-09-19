# Cloud SQL CRUD

## Prepare
write your connection name at 'resources/application.properties'.

```bash
# test table
CREATE TABLE kenko_record
(id char(4) not null,
name text not null,
food text not null,
PRIMARY KEY(id));
```

### CRUD oparate with cUrl
Browsing 'localhost:8080/' view all records.

use cUrl command (ex Command prompt)
```bash
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
