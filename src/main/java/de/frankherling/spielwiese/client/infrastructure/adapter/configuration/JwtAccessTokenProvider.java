package de.frankherling.spielwiese.client.infrastructure.adapter.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class JwtAccessTokenProvider {

    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable(value = "jwtToken", key = "#root.methodName", unless = "#result == null")
    public String getJwtToken() {

        String authServerUrl = "http://keycloak.local/realms/spielwiese-realm/protocol/openid-connect/token";
        String clientId = "spielwiese-client";
        String user = "dummy2";
        String password = "pwd";

        // Create the request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "password");
        requestBody.add("client_id", clientId);
        requestBody.add("username", user);
        requestBody.add("password", password);


        // Make the request to the authentication server
        log.info("Requesting JWT token from {}", authServerUrl);
        CustomJwt response = restTemplate.postForObject(authServerUrl, requestBody, CustomJwt.class);
        return response.getAccessToken();
    }
}
