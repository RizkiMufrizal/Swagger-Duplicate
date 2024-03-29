package org.rizki.mufrizal.swagger.client;

import java.util.List;
import org.rizki.mufrizal.swagger.client.object.ProxyResponse;

/**
 *
 * @author rizki
 */
public interface ProxyClient {

    List<ProxyResponse> getAllProxies(String ip, String port, String username, String password);
}
