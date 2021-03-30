package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SupplierDaoTest {

    SupplierDaoMem sdm;
    List<Supplier> slist;
    Supplier supplier;
    Supplier supplier2;

    @BeforeEach
    void setupMem(){
        slist = new ArrayList<>();
        sdm = SupplierDaoMem.getInstance();
        sdm.clearData();
        supplier = mock(Supplier.class);
        supplier2 = mock(Supplier.class);
    }


    @Test
    void add_addSupplier_increaseListSize() {
        int expectedSizeAfterAdd = 2;
        sdm.add(supplier);
        sdm.add(supplier2);
        Assertions.assertEquals(expectedSizeAfterAdd, sdm.getData().size());
    }

    @Test
    void find_validId_ReturnSupplier() {
        sdm.add(supplier);
        sdm.add(supplier2);
        when(supplier.getId()).thenReturn(1);
        when(supplier2.getId()).thenReturn(2);

        sdm.find(2);
        Assertions.assertEquals(supplier2, sdm.find(2));
    }

    @Test
    void find_invalidId_returnNull(){
        sdm.add(supplier);
        when(supplier.getId()).thenReturn(1);

        sdm.find(2);
        Assertions.assertNull(sdm.find(2));
    }

    @Test
    void remove_validId_removeSupplier() {
        sdm.add(supplier);
        sdm.add(supplier2);
        when(supplier.getId()).thenReturn(1);
        when(supplier2.getId()).thenReturn(2);
        slist.add(supplier);

        sdm.remove(2);

        Assertions.assertEquals(slist, sdm.getData());
    }

    @Test
    void remove_invalidId_removeNothing() {
        sdm.add(supplier);
        when(supplier.getId()).thenReturn(1);
        slist.add(supplier);

        sdm.remove(2);

        Assertions.assertEquals(slist, sdm.getData());
    }

    @Test
    void getAll_returnAllSupplier() {
        sdm.add(supplier);
        sdm.add(supplier2);
        slist.add(supplier);
        slist.add(supplier2);

        List<Supplier> sdmLst = sdm.getAll();

        Assertions.assertEquals(slist, sdmLst);
    }
}