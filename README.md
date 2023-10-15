# elasticsearch
## 설치
Dockerfile
```
ARG ELK_VERSION
FROM docker.elastic.co/elasticsearch/elasticsearch:${ELK_VERSION}
RUN elasticsearch-plugin install analysis-nori
```
<br/>elasticsearch.yml
```
version: '3.7'
services:
  es:
    build:    
      # 도커파일 위치
      context: ./
      # 인자 넣어주기
      args:
        ELK_VERSION: 8.7.0
    container_name: es
    environment:
      - node.name=single-node
      - cluster.name=backtony
      - discovery.type=single-node
      - xpack.security.enabled=false
      - xpack.security.enrollment.enabled=false
      - xpack.security.http.ssl.enabled=false
      - xpack.security.transport.ssl.enabled=false
    ports:
      - 9200:9200
      - 9300:9300
    networks:
      - es-bridge

  kibana:
    container_name: kibana
    image: docker.elastic.co/kibana/kibana:8.7.0
    environment:
      SERVER_NAME: kibana
      # Elasticsearch 기본 호스트는 http://elasticsearch:9200 이다. 
      # Elasticsearch 서비스 명은 es로 설정되어있다.
      ELASTICSEARCH_HOSTS: http://es:9200
    ports:
      - 5601:5601
    # Elasticsearch Start Dependency
    depends_on:
      - es
    networks:
      - es-bridge

networks:
  es-bridge:
    driver: bridge
```
## 실행
docker compose -f elasticsearch.yml up -d 
## API
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
