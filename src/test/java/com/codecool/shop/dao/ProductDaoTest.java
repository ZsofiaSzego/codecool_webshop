package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductDaoTest {

    ProductDaoMem pdm;
    List<Product> plist;
    Product product;
    Product product2;
    Supplier supplier;
    ProductCategory productCategory;

    @BeforeEach
    void setupMem(){
        plist = new ArrayList<>();
        pdm = ProductDaoMem.getInstance();
        pdm.clearData();
        product = mock(Product.class);
        product2 = mock(Product.class);
        supplier = mock(Supplier.class);
        productCategory = mock(ProductCategory.class);
    }

    @Test
    void add_addProduct_increaseListSize() {
        int expectedSizeAfterAdd = 2;
        pdm.add(product);
        pdm.add(product2);
        Assertions.assertEquals(expectedSizeAfterAdd, pdm.getData().size());
    }

    @Test
    void find_validId_removeProduct() {
        pdm.add(product);
        pdm.add(product2);
        when(product.getId()).thenReturn(1);
        when(product2.getId()).thenReturn(2);

        pdm.find(2);
        Assertions.assertEquals(product2, pdm.find(2));
    }

    @Test
    void find_invalidId_returnNull(){
        pdm.add(product);
        when(product.getId()).thenReturn(1);

        pdm.find(2);
        Assertions.assertNull(pdm.find(2));
    }

    @Test
    void remove_validId_removeProduct() {
        pdm.add(product);
        pdm.add(product2);
        when(product.getId()).thenReturn(1);
        when(product2.getId()).thenReturn(2);
        plist.add(product);

        pdm.remove(2);

        Assertions.assertEquals(plist, pdm.getData());
    }

    @Test
    void remove_invalidId_removeNothing() {
        pdm.add(product);
        when(product.getId()).thenReturn(1);
        plist.add(product);

        pdm.remove(2);

        Assertions.assertEquals(plist, pdm.getData());
    }


    @Test
    void getAll_returnAllProduct() {
        pdm.add(product);
        pdm.add(product2);
        plist.add(product);
        plist.add(product2);

        List<Product> pdmlst = pdm.getAll();

        Assertions.assertEquals(plist, pdmlst);
    }

    @Test
    void getBy_getBySupplier_returnProductsWithGivenSupplier() {
        pdm.add(product);
        pdm.add(product2);
        when(product.getSupplier()).thenReturn(supplier);
        Supplier supplier2 = mock(Supplier.class);
        when(product2.getSupplier()).thenReturn(supplier2);

        plist.add(product2);

        pdm.getBy(supplier2);

        Assertions.assertEquals(plist, pdm.getBy(supplier2));
    }

    @Test
    void getBy_getByProductCategory_returnProductsWithGivenCategory() {
        pdm.add(product);
        pdm.add(product2);
        when(product.getProductCategory()).thenReturn(productCategory);
        ProductCategory productCategory2 = mock(ProductCategory.class);
        when(product2.getProductCategory()).thenReturn(productCategory2);

        plist.add(product2);

        pdm.getBy(productCategory2);

        Assertions.assertEquals(plist, pdm.getBy(productCategory2));
    }

    @Test
    void getBy_getByProductCategoryAndSupplier_returnProductsWithGivenCategoryAndSupplier() {
        pdm.add(product);
        pdm.add(product2);

        when(product.getProductCategory()).thenReturn(productCategory);
        ProductCategory productCategory2 = mock(ProductCategory.class);
        when(product2.getProductCategory()).thenReturn(productCategory2);

        when(product.getSupplier()).thenReturn(supplier);
        Supplier supplier2 = mock(Supplier.class);
        when(product2.getSupplier()).thenReturn(supplier2);

        plist.add(product2);

        pdm.getBy(productCategory2, supplier2);

        Assertions.assertEquals(plist, pdm.getBy(productCategory2, supplier2));
    }
}