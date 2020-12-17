package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
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

class ProductCategoryDaoTest {

    ProductCategoryDaoMem pdm;
    List<ProductCategory> plist;
    ProductCategory product;
    ProductCategory product2;

    @BeforeEach
    void setupMem(){
        plist = new ArrayList<>();
        pdm = ProductCategoryDaoMem.getInstance();
        pdm.clearData();
        product = mock(ProductCategory.class);
        product2 = mock(ProductCategory.class);
    }

    @Test
    void add_addProductCategory_increaseSize() {
        int expectedSizeAfterAdd = 2;
        pdm.add(product);
        pdm.add(product2);
        Assertions.assertEquals(expectedSizeAfterAdd, pdm.getData().size());
    }

    @Test
    void find_validId_ReturnProductCategory() {
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
    void remove_validId_removeProductCategory() {
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
    void getAll_returnAllSupplier() {
        pdm.add(product);
        pdm.add(product2);
        plist.add(product);
        plist.add(product2);

        List<ProductCategory> pdmLst = pdm.getAll();

        Assertions.assertEquals(plist, pdmLst);
    }
}
