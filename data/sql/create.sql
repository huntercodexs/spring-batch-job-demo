CREATE TABLE `PRODUCTS` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `SALES` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `quantity` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `total` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO PRODUCTS (id, name, description, price) VALUES (1, 'Plastic Bags', 'Plastic bags to shopping', '40,00');
INSERT INTO PRODUCTS (id, name, description, price) VALUES (2, 'Wallet Plan', 'Money Wallet', '20,00');

INSERT INTO SALES (id, product, quantity, total) VALUES (1, 'Plastic Bags', '3', '120,00');
INSERT INTO SALES (id, product, quantity, total) VALUES (2, 'Wallet Plan', '2', '80,00');
