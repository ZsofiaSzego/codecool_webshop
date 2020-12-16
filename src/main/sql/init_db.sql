DROP TABLE IF EXISTS public.product_category;
CREATE TABLE public.product_category (
  id serial NOT NULL PRIMARY KEY,
  department varchar,
  name varchar,
  description varchar
);

DROP TABLE IF EXISTS public.supplier;
CREATE TABLE public.supplier (
 id serial NOT NULL PRIMARY KEY,
 name varchar,
 description varchar
);

DROP TABLE IF EXISTS public.products;
CREATE TABLE public.products (
  id serial NOT NULL PRIMARY KEY,
  name varchar,
  default_price float,
  currency varchar,
  description varchar,
  supplier integer,
  product_category integer
);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_product_category FOREIGN KEY (product_category) REFERENCES public.product_category(id);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_supplier FOREIGN KEY (supplier) REFERENCES public.supplier(id);

INSERT INTO public.supplier (name, description) VALUES ('Amazon', 'Digital content and services');
INSERT INTO public.supplier (name, description) VALUES ('Lenovo', 'Computers');

INSERT INTO public.product_category (department, name, description) VALUES ('Hardware', 'Tablet', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO public.product_category (department, name, description) VALUES ('Hardware', 'Mouse', 'A computer mouse is a handheld hardware input device that controls a cursor for desktop computers.');

INSERT INTO public.products (name, default_price, currency, description, supplier, product_category) VALUES
('Amazon Fire', '49.9f', 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 0, 0);
INSERT INTO public.products (name, default_price, currency, description, supplier, product_category) VALUES
('Lenovo IdeaPad Miix 700', '479', 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 1, 0);
INSERT INTO public.products (name, default_price, currency, description, supplier, product_category) VALUES
('Amazon Fire HD 8', '89', 'USD', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.', 0, 0);
INSERT INTO public.products (name, default_price, currency, description, supplier, product_category) VALUES
('Lenovo Fire M 9', '50', 'USD', '2.4G Wireless and Type-c Mouse:Jelly Comb wireless mouse is very convenient,provided 2-1 connections-USB and Type-c Nano receiver,plug and play,no need installation other device.the signal is stable and connect 10m distance，could offer best experience.', 1, 1);
