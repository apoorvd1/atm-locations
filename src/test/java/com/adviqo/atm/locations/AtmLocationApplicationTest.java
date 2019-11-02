package com.adviqo.atm.locations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AtmLocationApplicationTest {

    @InjectMocks
    private AtmLocationApplication atmLocationApplication;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void main() {
        AtmLocationApplication.main(new String[]{"Hello", "World"});
    }

    @Test
    public void shouldTestSpringApplicationBuilder() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(AtmLocationApplication.class);
        AtmLocationApplication atmLocationApplication = new AtmLocationApplication();
        SpringApplicationBuilder springApplicationBuilder = atmLocationApplication.configure(builder);
        assertNotNull(springApplicationBuilder);
    }
}