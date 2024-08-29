package net.giuseppe.wine_shop.util;

import net.giuseppe.wine_shop.consumer.model.Consumer;
import org.jeasy.random.EasyRandom;

import java.util.List;

public class TestMocks {
    public static final List<Consumer> CONSUMER_LIST_MOCK = List.of(
            new EasyRandom().nextObject(Consumer.class),
            new EasyRandom().nextObject(Consumer.class)
    );
}
