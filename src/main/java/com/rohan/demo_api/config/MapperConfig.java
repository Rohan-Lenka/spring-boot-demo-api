package com.rohan.demo_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    // Why was making its bean necessary, can't spring do its DI directly ?
    // Answer is no coz spring can't scan for components which are in external packages.
}
