package com.adviqo.atm.locations.health.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LocatorIndicators implements HealthIndicator {

    private final RestTemplate restTemplate;

    @Value("${ingLocatorHost}")
    private String ingLocatorHost;

    @Autowired
    public LocatorIndicators(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Return an indication of health.
     *
     * @return the health for
     */
    @Override
    public Health health() {
        if (!isUp()) {
            return Health.down().withDetail("Error getting from atm location service", 500).build();
        }
        return Health.up().build();
    }

    private boolean isUp() {
        try {
            restTemplate.getForEntity(
                    ingLocatorHost,
                    String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
