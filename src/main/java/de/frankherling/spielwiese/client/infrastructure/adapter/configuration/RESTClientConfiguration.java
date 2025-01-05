package de.frankherling.spielwiese.client.infrastructure.adapter.configuration;

import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.ApiClient;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.client.OrdersApi;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.payment.client.PaymentsApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RESTClientConfiguration {


    @Bean
    public RestClient restClient(RestClient.Builder builder, JwtRequestInterceptor jwtRequestInterceptor) {
        return builder.requestInterceptor(jwtRequestInterceptor).build();
    }


    @Bean
    ApiClient apiClientOrder(RestClient restClient) {
        return new ApiClient(restClient);
    }

    @Bean
    de.frankherling.spielwiese.app.infrastructure.adapter.rest.payment.ApiClient apiClientPayment(RestClient restClient) {
        return new de.frankherling.spielwiese.app.infrastructure.adapter.rest.payment.ApiClient(restClient);
    }

    @Bean
    OrdersApi ordersApi(ApiClient apiClientOrder) {
        return new OrdersApi(apiClientOrder);
    }


    @Bean
    ApiClient apiClient(RestClient restClient) {
        return new ApiClient(restClient);
    }

    @Bean
    PaymentsApi paymentsApi(de.frankherling.spielwiese.app.infrastructure.adapter.rest.payment.ApiClient apiClientPayment) {
        return new PaymentsApi(apiClientPayment);
    }

}
