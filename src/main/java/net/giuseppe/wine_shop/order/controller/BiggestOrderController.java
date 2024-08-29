package net.giuseppe.wine_shop.order.controller;

import net.giuseppe.wine_shop.common.controller.BaseController;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.order.service.BiggestOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maior-compra")
public class BiggestOrderController extends BaseController {

    @Autowired
    private BiggestOrderService service;


    @GetMapping(path = "{ano}", consumes = CONSUMES_ALL, produces = PRODUCES_JSON)
    public BaseResponse getBiggestOrder(@PathVariable("ano") Integer year) {
        return service.getBiggestOrderForYear(year);
    }

}
