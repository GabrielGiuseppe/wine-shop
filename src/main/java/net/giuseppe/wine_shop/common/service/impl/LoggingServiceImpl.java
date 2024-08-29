package net.giuseppe.wine_shop.common.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.giuseppe.wine_shop.common.service.LoggingService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

@Component
public class LoggingServiceImpl implements LoggingService {

    private static final Logger log = LoggerFactory.getLogger(LoggingServiceImpl.class);

    @Override
    public void logRequest(HttpServletRequest request, Object body) {

        String bodyText = null;

        try {
            if (Strings.isNotBlank(bodyText)) {
                ObjectMapper mapper = new ObjectMapper();
                bodyText = mapper.writeValueAsString(body);
            }
        } catch (JsonProcessingException e) {
            bodyText = "No Body";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n\nREQUEST ---> %s %s HTTP/1.1\n\n", request.getMethod(), request.getRequestURL()));

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            sb.append(String.format("%s: %s\n", key, value));
        }

        sb.append(String.format("\n%s", Strings.isNotBlank(bodyText) ? bodyText : "No Body"));
        sb.append(String.format("\n\n---> END REQUEST HTTP (%s-byte body)\n\n", Strings.isNotBlank(bodyText) ? bodyText.length() : 0));

        log.info(sb.toString());
    }

    @Override
    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {

        String bodyText = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            bodyText = mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            bodyText = "No Body";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n\nRESPONSE <--- HTTP/1.1 %s (%sms)\n\n", response.getStatus(), 0));

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            sb.append(String.format("%s: %s\n", key, value));
        }

        sb.append(String.format("\n%s", Strings.isNotBlank(bodyText) ? bodyText : "No Body"));
        sb.append(String.format("\n\n<--- END RESPONSE HTTP (%s-byte body)\n\n", Strings.isNotBlank(bodyText) ? bodyText.length() : 0));

        log.info(sb.toString());
    }
}
