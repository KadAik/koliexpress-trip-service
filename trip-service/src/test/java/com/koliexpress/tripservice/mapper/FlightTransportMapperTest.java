package com.koliexpress.tripservice.mapper;

import com.koliexpress.tripservice.builder.model.transport.FlightTransportTestBuilder;
import com.koliexpress.tripservice.dto.transport.FlightTransportResponseDto;
import com.koliexpress.tripservice.mapper.transport.FlightTransportMapper;
import com.koliexpress.tripservice.model.transport.FlightTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("FlightTransportMapper Tests")
public class FlightTransportMapperTest {

    @Autowired
    private FlightTransportMapper flightTransportMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Qualifier("jdbcTemplate")
    @Autowired
    private Object object;

    @Test
    void testToResponseDto_whenGivenValidEntity_shouldMapChildAndBaseClassFields() throws Exception{
        FlightTransport entity = FlightTransportTestBuilder
                .aFlight()
                .build();

        FlightTransportResponseDto dto = flightTransportMapper.toResponseDto(entity);

        System.out.println(entity);
        System.out.println(objectMapper.writeValueAsString(dto));

        assertThat(dto).isNotNull();

        // Check for the presence of flight details fields
        assertThat(dto).hasFieldOrProperty("departureAirportCode");
        assertThat(dto.getDepartureAirportCode()).isNotNull();

        // Check for the presence of Base Transport fields
        assertThat(dto).hasFieldOrProperty("providerName");
        assertThat(dto.getProviderName()).isNotNull();
    }
}
