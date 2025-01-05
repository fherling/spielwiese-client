package de.frankherling.spielwiese.client.infrastructure.adapter.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestInterceptor implements ClientHttpRequestInterceptor {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Add the JWT token to the Authorization header
        request.getHeaders().set("Authorization", "Bearer " + getJwtToken());
        return execution.execute(request, body);
    }

    private String getJwtToken() {
        // Implement the logic to retrieve the JWT token for the technical user
        return jwtAccessTokenProvider.getJwtToken();
    }
}
