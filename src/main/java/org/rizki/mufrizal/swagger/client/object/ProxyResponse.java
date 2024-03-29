package org.rizki.mufrizal.swagger.client.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author rizki
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProxyResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("organizationId")
    private String organizationId;

    @JsonProperty("apiId")
    private String apiId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("version")
    private String version;

    @JsonProperty("apiRoutingKey")
    private String apiRoutingKey;

    @JsonProperty("vhost")
    private String vhost;

    @JsonProperty("path")
    private String path;

    @JsonProperty("retired")
    private Boolean retired;

    @JsonProperty("expired")
    private Boolean expired;

    @JsonProperty("deprecated")
    private Boolean deprecated;

    @JsonProperty("state")
    private String state;

    private String message;
}
