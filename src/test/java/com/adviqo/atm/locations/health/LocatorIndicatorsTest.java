package com.adviqo.atm.locations.health;

import com.adviqo.atm.locations.AtmLocationApplication;
import com.adviqo.atm.locations.health.model.LocatorIndicators;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = AtmLocationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocatorIndicatorsTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LocatorIndicators locatorIndicators;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(locatorIndicators,
                "ingLocatorHost",
                " https://www.ing.nl/api/locator/atms/");
        locatorIndicators = new LocatorIndicators(restTemplate);
    }

    @Test
    public void shouldReturnTheActiveProfile() {
        Health health = locatorIndicators.health();
        Assert.assertEquals("UP", health.getStatus().getCode());
    }

}
