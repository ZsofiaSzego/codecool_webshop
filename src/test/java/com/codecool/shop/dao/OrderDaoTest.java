package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderDaoTest {
    OrderDaoMem od;
    Order order;
    List<Order> orderList;

    @BeforeEach
    void setUp(){
        od = OrderDaoMem.getInstance();
        order = mock(Order.class);
        orderList = od.getData();
    }

    @Test
    void add_addValidOrder_dataSizeIncrease() {
        int expectedLstSize =1;

        od.add(order);

        assertEquals(expectedLstSize, od.getData().size());
    }

    @Test
    void find_validOrderId_findOrderById() {
         Order order2 =  mock(Order.class);
         when(order.getId()).thenReturn(1);
         when(order2.getId()).thenReturn(2);
         orderList.add(order);
         orderList.add(order2);

         Order o = od.find(2);

         assertEquals(order2.getId(), o.getId());
    }

    @Test
    void find_invalidId_returnNull(){
        when(order.getId()).thenReturn(1);
        orderList.add(order);

        Order o2 = od.find(2);

        assertNull(o2);
    }

    @Test
    void remove_validId_removeOrderById() {
        when(order.getId()).thenReturn(1);
        Order order2 = mock(Order.class);
        when(order2.getId()).thenReturn(2);
        orderList.add(order);
        orderList.add(order2);

        od.remove(2);


    }

    @Test
    void remove_invalidId_noChangeInOrderListSize() {
    }


    @Test
    void getAll_EmptyOrderList_ReturnEmptyList() {
    }

    @Test
    void getAll_OrderListHaveOrder_returnListWithSameSize(){
    }
}