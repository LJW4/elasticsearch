# elasticsearch

데이터 저장
```
POST http://localhost:8080/product
{
    name: "지갑",
    price: 30000,
    category: "액세서리",
}
```
전체 조회
```
GET http://localhost:8080/products
[
    {
        "id": 1,
        "name": "지갑",
        "price": 30000,
        "category": "액세서리",
        "views": 0,
	"likes": 0,
    }
]
```

조건 조회
```
GET http://localhost:8080/product/category?name=액세서리
[
    {
        "id": 1,
        "name": "지갑",
        "price": 30000,
        "category": "액세서리",
        "views": 0,
	"likes": 0,
    }
]
```
