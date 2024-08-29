package net.giuseppe.wine_shop.order.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.model.Consumer;
import net.giuseppe.wine_shop.consumer.service.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_CONSUMER_FOUND;
import static net.giuseppe.wine_shop.util.TestMocks.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    @Mock
    private ConsumerService consumerService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrderListSuccess() {
        var mockResponse = new BaseResponse(CONSUMER_LIST_MOCK);

        when(consumerService.getcostumerList()).thenReturn(mockResponse);

        BaseResponse<List<Consumer>> response = orderService.getOrderList();
        List<Consumer> resultList = response.data;

        assertTrue(response.success);
        assertNotNull(response.data);
        assertEquals(CONSUMER_LIST_MOCK, response.data);
        assertEquals(CONSUMER_LIST_MOCK.size(), resultList.size());
    }

    @Test
    public void testGetOrderEmptyListNoConsumerFound() {
        when(consumerService.getcostumerList()).thenReturn(new BaseResponse(null));

        BaseException exception = assertThrows(BaseException.class, () -> {
            orderService.getOrderList();
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(NO_CONSUMER_FOUND, exception.getMessage());
    }

    @Test
    public void testGetOrderNullListNoConsumerFound() {
        when(consumerService.getcostumerList()).thenReturn(new BaseResponse());

        BaseException exception = assertThrows(BaseException.class, () -> {
            orderService.getOrderList();
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(NO_CONSUMER_FOUND, exception.getMessage());
    }
}