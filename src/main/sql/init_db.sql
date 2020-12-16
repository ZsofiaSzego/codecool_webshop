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
