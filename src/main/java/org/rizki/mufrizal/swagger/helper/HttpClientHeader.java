package org.rizki.mufrizal.swagger.helper;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rizki
 */
public class HttpClientHeader {

    public static Map<String, String> setAuthorization(String username, String password) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
        return headers;
    }

}
