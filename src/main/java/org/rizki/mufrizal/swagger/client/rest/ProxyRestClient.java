package org.rizki.mufrizal.swagger.client.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.rizki.mufrizal.swagger.client.ProxyClient;
import org.rizki.mufrizal.swagger.client.object.ProxyResponse;
import org.rizki.mufrizal.swagger.configuration.HttpComponentExecution;
import org.rizki.mufrizal.swagger.helper.Constant;
import org.rizki.mufrizal.swagger.helper.HttpClientHeader;
import org.rizki.mufrizal.swagger.helper.LoggerHelper;

/**
 *
 * @author rizki
 */
public class ProxyRestClient implements ProxyClient {

    @Override
    public List<ProxyResponse> getAllProxies(String ip, String port, String username, String password) {
        try {
            Map<String, String> headers = HttpClientHeader.setAuthorization(username, password);

            int connectTimeout = 10000;
            int socketTimeout = 10000;
            HttpComponentExecution httpComponentExecution = new HttpComponentExecution("GET", Constant.PROXY_URL(ip, port));
            return Arrays.asList(httpComponentExecution.executeJson(connectTimeout, socketTimeout, null, headers, ProxyResponse[].class));
        } catch (Exception e) {
            LoggerHelper.LogError(e, "Exception {}");
        }
        return null;

    }

}
