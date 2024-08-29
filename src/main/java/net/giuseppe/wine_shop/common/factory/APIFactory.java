package net.giuseppe.wine_shop.common.factory;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import net.giuseppe.wine_shop.common.configuration.FeignErrorDecoder;
import net.giuseppe.wine_shop.consumer.api.ConsumerAPI;
import net.giuseppe.wine_shop.product.api.ProductAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static net.giuseppe.wine_shop.common.environments.Environments.API_HOST;
import static net.giuseppe.wine_shop.common.environments.Environments.API_READ_TIMEOUT;

@Configuration
public class APIFactory {

    @Autowired
    private Environment env;

    @Bean
    public ConsumerAPI clientsApi() {
        return Feign.builder()
                .options(new Request.Options(getTimeout(), getReadTimeout()))
                .logger(new Slf4jLogger())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .errorDecoder(new FeignErrorDecoder())
                .retryer(Retryer.NEVER_RETRY)
                .logLevel(Logger.Level.FULL)
                .target(ConsumerAPI.class, getHost());
    }

    @Bean
    public ProductAPI productAPI() {
        return Feign.builder()
                .options(new Request.Options(getTimeout(), getReadTimeout()))
                .logger(new Slf4jLogger())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .errorDecoder(new FeignErrorDecoder())
                .retryer(Retryer.NEVER_RETRY)
                .logLevel(Logger.Level.FULL)
                .target(ProductAPI.class, getHost());
    }

    private int getReadTimeout() {
        return env.getProperty(API_READ_TIMEOUT, Integer.class) * 1000;
    }

    private int getTimeout() {
        return env.getProperty(API_READ_TIMEOUT, Integer.class) * 1000;
    }

    private String getHost() {
        return env.getProperty(API_HOST, String.class);
    }
}
