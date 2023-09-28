# elasticsearch

## API List
<br/>저장
```
POST http://localhost:8080/product
{
    "name": "지갑",
    "price": 30000,
    "category": "액세서리"
}
```
<br/>Elasticsearch 저장
```
POST http://localhost:8080/product/1/document
```
<br/>수정
```
PATCH http://localhost:8080/product/1
{
    "price": 50000
}
```
<br/>Elasticsearch 조회
```
GET http://localhost:8080/product/document/1
```
```
{
    "id": 1,
    "category": "액세서리",
    "name": "지갑",
    "price": 50000,
    "views": 0,
    "likes": 0
}
```
<br/>좋아요
```
PATCH http://localhost:8080/product/1/likes/1
```
