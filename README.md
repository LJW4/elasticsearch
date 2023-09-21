# elasticsearch

데이터 저장
POST http://localhost:8080/product
{
	id: 1,
    name: "지갑",
    price: 30000,
    description: "가죽지갑",
    quantity: 10
}

전체 조회
GET http://localhost:8080/products
[
    {
        "id": 1,
        "name": "지갑",
        "price": 30000,
        "description": "가죽지갑",
        "quantity": 10,
        "createAt": "2023-09-17T22:42:59.694"
    },
    {
        "id": 2,
        "name": "선풍기",
        "price": 50000,
        "description": "스탠드형 선풍기",
        "quantity": 4,
        "createAt": "2023-09-17T23:51:20.199"
    }
]

조건 조회
GET http://localhost:8080/product/name?name=지갑
[
  {
      "id": 1,
      "name": "지갑",
      "price": 30000,
      "description": "가죽지갑",
      "quantity": 10,
      "createAt": "2023-09-17T22:42:59.694"
  }
]
