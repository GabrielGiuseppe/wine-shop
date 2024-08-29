package net.giuseppe.wine_shop.consumer.controller;

import net.giuseppe.wine_shop.common.controller.BaseController;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.service.ClientWineTypeRecomendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recomendacao")
public class ClientWineTypeRecomendationController extends BaseController {

    @Autowired
    private ClientWineTypeRecomendationService service;

    @GetMapping(path = "/cliente/tipo", consumes = CONSUMES_ALL, produces = PRODUCES_JSON)
    public BaseResponse getGeneralWineTypeRecomendation() {
        return service.getGeneralWineTypeRecomendation();
    }

    @GetMapping(path = "/{cliente}/tipo", consumes = CONSUMES_ALL, produces = PRODUCES_JSON)
    public BaseResponse getWineTypeRecomendation(@PathVariable("cliente") String consumerName) {
        return service.getWineTypeRecomendation(consumerName);
    }
}
