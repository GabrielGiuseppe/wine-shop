package net.giuseppe.wine_shop.consumer.controller;

import net.giuseppe.wine_shop.common.controller.BaseController;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.service.MostOrdersMadeConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/clientes-fieis")
public class MostOrdersMadeConsumerController extends BaseController {

    @Autowired
    private MostOrdersMadeConsumerService service;

    @GetMapping(consumes = CONSUMES_ALL, produces = PRODUCES_JSON)
    public BaseResponse getMostOrdersMadeConsumer() {
        return service.getTop3MostOrdersMadeConsumer();
    }
}
