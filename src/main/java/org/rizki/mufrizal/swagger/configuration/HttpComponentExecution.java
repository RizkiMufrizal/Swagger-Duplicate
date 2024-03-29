package org.rizki.mufrizal.swagger.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.rizki.mufrizal.swagger.helper.LoggerHelper;

@Slf4j
public class HttpComponentExecution extends HttpUriRequestBase {

    public HttpComponentExecution(String method, String url) {
        super(method, URI.create(url));
    }

    public <T> T executeJson(int connectTimeout, int activeTimeout, Object requestBody, Map<String, String> headers, Class<T> tClass) throws Exception {
        headers.entrySet().parallelStream().forEach(header -> this.addHeader(header.getKey(), header.getValue()));
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        if (requestBody != null) {
            try {
                this.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
            } catch (JsonProcessingException e) {
                LoggerHelper.LogError(e, "Exception {}");
                throw new RuntimeException(e);
            }
        }

        Map<String, Object> mapLog = new HashMap<>();
        Map<String, Object> mapLogRequest = new HashMap<>();

        mapLogRequest.put("request_url", this.getUri());
        mapLogRequest.put("request_method", this.getMethod());
        mapLogRequest.put("request_header", this.getHeaders());
        mapLogRequest.put("request_body", requestBody);
        mapLog.put("request", mapLogRequest);

        try (CloseableHttpClient closeableHttpClient = HttpComponentConfiguration.config(connectTimeout, activeTimeout)) {
            long t1 = System.nanoTime();
            return closeableHttpClient.execute(this, response -> {
                long t2 = System.nanoTime();
                String responseBody = EntityUtils.toString(response.getEntity());
                T tObject = objectMapper.readValue(responseBody, tClass);

                Map<String, Object> mapLogResponse = new HashMap<>();
                mapLogResponse.put("response_time", this.getRequestUri() + " in " + ((t2 - t1) / 1e6d) + "ms");
                mapLogResponse.put("response_header", response.getHeaders());
                mapLogResponse.put("response_status", response.getCode());
                mapLogResponse.put("response_message", response.getReasonPhrase());
                mapLogResponse.put("response_version", response.getVersion().getMajor() + "/" + response.getVersion().getMinor());
                mapLogResponse.put("response_body", tObject);
                mapLog.put("response", mapLogResponse);

                log.info(objectMapper.writeValueAsString(mapLog));
                return tObject;
            });
        } catch (Exception e) {
            log.info(objectMapper.writeValueAsString(mapLog));
            LoggerHelper.LogError(e, "Exception {}");
            throw new RuntimeException(e);
        }
    }

    public Integer executeJson(int connectTimeout, int activeTimeout, Object requestBody, Map<String, String> headers) throws Exception {
        headers.entrySet().parallelStream().forEach(header -> this.addHeader(header.getKey(), header.getValue()));
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        if (requestBody != null) {
            try {
                this.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
            } catch (JsonProcessingException e) {
                LoggerHelper.LogError(e, "Exception {}");
                throw new RuntimeException(e);
            }
        }

        Map<String, Object> mapLog = new HashMap<>();
        Map<String, Object> mapLogRequest = new HashMap<>();

        mapLogRequest.put("request_url", this.getUri());
        mapLogRequest.put("request_method", this.getMethod());
        mapLogRequest.put("request_header", this.getHeaders());
        mapLogRequest.put("request_body", requestBody);
        mapLog.put("request", mapLogRequest);

        try (CloseableHttpClient closeableHttpClient = HttpComponentConfiguration.config(connectTimeout, activeTimeout)) {
            long t1 = System.nanoTime();
            return closeableHttpClient.execute(this, response -> {
                long t2 = System.nanoTime();
                String responseBody = EntityUtils.toString(response.getEntity());

                Map<String, Object> mapLogResponse = new HashMap<>();
                mapLogResponse.put("response_time", this.getRequestUri() + " in " + ((t2 - t1) / 1e6d) + "ms");
                mapLogResponse.put("response_header", response.getHeaders());
                mapLogResponse.put("response_status", response.getCode());
                mapLogResponse.put("response_message", response.getReasonPhrase());
                mapLogResponse.put("response_version", response.getVersion().getMajor() + "/" + response.getVersion().getMinor());
                mapLogResponse.put("response_body", responseBody);
                mapLog.put("response", mapLogResponse);

                log.info(objectMapper.writeValueAsString(mapLog));
                return response.getCode();
            });
        } catch (Exception e) {
            log.info(objectMapper.writeValueAsString(mapLog));
            LoggerHelper.LogError(e, "Exception {}");
            throw new RuntimeException(e);
        }
    }
}
