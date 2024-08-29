package net.giuseppe.wine_shop.consumer.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.api.ConsumerAPI;
import net.giuseppe.wine_shop.consumer.model.Consumer;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.*;
import static net.giuseppe.wine_shop.util.TestMocks.CONSUMER_LIST_MOCK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsumerServiceImplTest {

    @Mock
    private ConsumerAPI consumerAPI;

    @InjectMocks
    private ConsumerServiceImpl consumerService;

    private EasyRandom easyRandom;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        easyRandom = new EasyRandom();
    }

    @Test
    public void testGetConsumerListSuccess() {
        List<Consumer> mockConsumerList = CONSUMER_LIST_MOCK;
        when(consumerAPI.getConsumerList()).thenReturn(mockConsumerList);

        BaseResponse<List<Consumer>> response = consumerService.getcostumerList();
        List<Consumer> resultList = response.data;

        assertTrue(response.success);
        assertNotNull(response.data);
        assertEquals(CONSUMER_LIST_MOCK, response.data);
        assertEquals(CONSUMER_LIST_MOCK.size(), resultList.size());
    }

    @Test
    public void testGetConsumerEmptyListNoConsumersFound() {
        when(consumerAPI.getConsumerList()).thenReturn(Collections.emptyList());

        BaseException exception = assertThrows(BaseException.class, () -> {
            consumerService.getcostumerList();
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(NO_CONSUMER_FOUND, exception.getMessage());
        verify(consumerAPI, times(1)).getConsumerList();
    }

    @Test
    public void testGetConsumerNullListNoConsumersFound() {
        when(consumerAPI.getConsumerList()).thenReturn(null);

        BaseException exception = assertThrows(BaseException.class, () -> {
            consumerService.getcostumerList();
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(NO_CONSUMER_FOUND, exception.getMessage());
        verify(consumerAPI, times(1)).getConsumerList();
    }
}