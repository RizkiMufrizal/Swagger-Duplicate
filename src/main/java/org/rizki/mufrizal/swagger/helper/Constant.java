package org.rizki.mufrizal.swagger.helper;

/**
 *
 * @author rizki
 */
public class Constant {

    private static final String HTTPS = "https://";
    private static final String VERSION = "v1.4";
    private static final String BASE_URL = "/api/portal";

    public static String PROXY_URL(String ip, String port) {
        return HTTPS + ip + port + BASE_URL + "/" + VERSION + "/proxies/light";
    }
}
