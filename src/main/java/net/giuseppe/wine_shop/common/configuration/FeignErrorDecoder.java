package net.giuseppe.wine_shop.common.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.Reader;

public class FeignErrorDecoder implements ErrorDecoder {

    Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {

        String erroMessage = null;
        Reader reader = null;

        try {
            reader = response.body().asReader();
            String result = reader.toString();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            BaseResponse<Object> exceptionMessage = mapper.readValue(result,
                    BaseResponse.class);
            erroMessage = exceptionMessage.description;

        } catch (IOException e) {
            logger.error("IO Exception on reading exception message feign client" + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                logger.error("IO Exception on reading exception message feign client" + e);
            }
        }

        switch (response.status()) {
            case 400:
                return new BaseException(HttpStatus.BAD_REQUEST, erroMessage);
            case 401:
                return new BaseException(HttpStatus.UNAUTHORIZED, erroMessage);
            case 404:
                return new BaseException(HttpStatus.NOT_FOUND, erroMessage);
            default:
                return new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, erroMessage);
        }
    }

}
