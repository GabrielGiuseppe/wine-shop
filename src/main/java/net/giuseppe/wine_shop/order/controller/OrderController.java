package net.giuseppe.wine_shop.order.controller;

import net.giuseppe.wine_shop.common.controller.BaseController;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class OrderController extends BaseController {

    @Autowired
    OrderService service;

    @GetMapping(consumes = CONSUMES_ALL, produces = PRODUCES_JSON)
    public BaseResponse getOrderList() {
        return service.getOrderList();
    }
}
