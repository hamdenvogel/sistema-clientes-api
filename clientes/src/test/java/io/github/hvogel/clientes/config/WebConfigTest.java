package io.github.hvogel.clientes.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.filter.CorsFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WebConfigTest {

    @Test
    void testCorsFilterRegistrationBean() {
        WebConfig webConfig = new WebConfig();
        FilterRegistrationBean<CorsFilter> filterRegistrationBean = webConfig.corsFilterRegistrationBean();

        assertNotNull(filterRegistrationBean);
        assertEquals(Integer.MIN_VALUE, filterRegistrationBean.getOrder());
    }
}
