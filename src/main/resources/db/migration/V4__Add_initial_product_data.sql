INSERT INTO
    customers (id, email, username, password, authority, address, phone)
VALUES
    ('3395a42e-2d88-40de-b95f-e00e1502085b', 'hong@gmail.com', 'hong', 'hong1234', 'ROLE_USER', 'address1', '123'),
    ('3395a43e-2d88-40de-b95f-e00e1502085b', 'tommy@gmail.com', 'tommy', 'tommy1234', 'ROLE_USER', 'address2', '321'),
    ('837ab141-399e-4c1f-9abc-bace40296bac', 'hanh@gmx.de', 'hanh', 'hanh1234', 'ROLE_USER', 'address3', '231'),
    ('12c77898-0281-408d-88d6-b10c2bcfe623', 'mama@gmx.de', 'mama', 'mama1234', 'ROLE_USER', 'address4', '1234'),
    ('e80d3c15-4b7b-4fed-9e24-3d219d882e50', 'papa@gmx.de', 'admin', 'admin', 'ROLE_ADMIN', 'Marx MuterStr.5', '2341');


INSERT INTO
    carts (id, created_at, user_id)
VALUES
    ('6d62d909-f957-430e-8689-b5129c0bb75e', '2025-12-31 10:00:56', '3395a43e-2d88-40de-b95f-e00e1502085b'),
    ('a0a4f044-b040-410d-8ead-4de0446aec7e', '2025-12-31 10:01:35', '3395a42e-2d88-40de-b95f-e00e1502085b'),
    ('808a2de1-1aaa-4c25-a9b9-6612e8f29a38', '2026-01-11 10:01:35', '837ab141-399e-4c1f-9abc-bace40296bac'),
    ('48bf7ba3-2cfa-4105-b092-118a4d9075d8', '2026-01-15 11:01:35', '12c77898-0281-408d-88d6-b10c2bcfe623');


INSERT INTO
    cartitems (id, qty, unit_price, product_id, cart_id)
VALUES
    ('ca3d3d42-9379-4ba4-bf3e-a09ec3efbabe', 2, 19.99, '510a0d7e-8e83-4193-b483-e27e09ddc34d', '6d62d909-f957-430e-8689-b5129c0bb75e'),
    ('b9f05831-6373-4303-b178-00ba325ca301', 1, 39.99, '03fef6ac-1896-4ce8-bd69-b798f85c6e0b', 'a0a4f044-b040-410d-8ead-4de0446aec7e'),
    ('092b9522-e424-497d-ab06-4637ad824592', 1, 49.99, 'd3588630-ad8e-49df-bbd7-3167f7efb246', 'a0a4f044-b040-410d-8ead-4de0446aec7e'),
    ('be4a655a-a61d-441e-9b71-455542824d5f', 2, 19.99, '510a0d7e-8e83-4193-b483-e27e09ddc34d', '808a2de1-1aaa-4c25-a9b9-6612e8f29a38'),
    ('39a10f57-5e91-4625-9bc1-51afbbbff40c', 1, 49.99, 'd3588630-ad8e-49df-bbd7-3167f7efb246', '808a2de1-1aaa-4c25-a9b9-6612e8f29a38');