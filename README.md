## spring boot 3 SHOP version 2

# run API server

cd C:\HONG\JAVA2026\Sym3SHOP\HONG

git clone https://github.com/hong1234/springboot3ShopApiV3.git

cd springboot3ShopApiV3

./mvnw spring-boot:run

# run test

./mvnw test

# test using UI-App sb3ShopUI-ReactTanStack

git clone https://github.com/hong1234/sb3ShopUI-ReactTanStack.git

cd sb3ShopUI-ReactTanStack

npm install

npm run dev

// browser

http://localhost:5173/

# test using Postman client

get token

GET http://localhost:8000/api/v1/auth/signin

# using token for access

http://localhost:8080/api/v1/products/search?title=JavaScript

http://localhost:8080/api/v1/categories/a1b9b31d-e73c-4112-af7c-b68530f38222

http://localhost:8080/api/v1/categories/819e1fbf-8b7e-4f6d-811f-693534916a8b

http://localhost:8080/api/v1/carts/3395a42e-2d88-40de-b95f-e00e1502085b

http://localhost:8080/api/v1/carts/3395a42e-2d88-40de-b95f-e00e1502085b/checkout

=> example "orderId": "892422cd-0835-479e-84de-9265d3f2dc6a"

http://localhost:8080/api/v1/orders/892422cd-0835-479e-84de-9265d3f2dc6a

http://localhost:8080/api/v1/carts/3395a43e-2d88-40de-b95f-e00e1502085b

http://localhost:8080/api/v1/carts/3395a43e-2d88-40de-b95f-e00e1502085b/checkout

=> example "orderId": "f837158c-06ba-4da4-b5ed-ef40047c0393"

http://localhost:8080/api/v1/orders/f837158c-06ba-4da4-b5ed-ef40047c0393

http://localhost:8080/api/v1/carts/837ab141-399e-4c1f-9abc-bace40296bac

http://localhost:8080/api/v1/carts/837ab141-399e-4c1f-9abc-bace40296bac/checkout

=> example "orderId": "8af722c6-342c-4fc8-a48f-ead9f90eb2f0"

http://localhost:8080/api/v1/orders/8af722c6-342c-4fc8-a48f-ead9f90eb2f0

PUT
http://localhost:8080/api/v1/carts/3395a43e-2d88-40de-b95f-e00e1502085b/items

{
"modus": "add",
"qty": "1",
"productId": "892422cd-0835-479e-84de-9265d3f2dc6a"
}

http://localhost:8080/api/v1/carts/3395a43e-2d88-40de-b95f-e00e1502085b/checkout

=> orderId: "2a5ae18c-b62d-47cf-80b1-2d3fba17e88f"

http://localhost:8080/api/v1/orders/2a5ae18c-b62d-47cf-80b1-2d3fba17e88f

GET http://localhost:8080/api/v1/carts/12c77898-0281-408d-88d6-b10c2bcfe623

PUT http://localhost:8080/api/v1/carts/12c77898-0281-408d-88d6-b10c2bcfe623/items

{
"modus": "add",
"productId": "03fef6ac-1896-4ce8-bd69-b798f85c6e0b"
}

PUT http://localhost:8080/api/v1/carts/12c77898-0281-408d-88d6-b10c2bcfe623/items

{
"modus": "remove",
"productId": "03fef6ac-1896-4ce8-bd69-b798f85c6e0b"
}

GET http://localhost:8080/api/v1/carts/12c77898-0281-408d-88d6-b10c2bcfe623/checkout

GET http://localhost:8080/api/v1/carts/e80d3c15-4b7b-4fed-9e24-3d219d882e50

## add product

POST http://localhost:8080/api/v1/products

{
"title": "test",
"supplier": "test",
"image": "test",
"unitPrice": 19.99,
"categoryId": "a1b9b31d-e73c-4112-af7c-b68530f38222"
}
