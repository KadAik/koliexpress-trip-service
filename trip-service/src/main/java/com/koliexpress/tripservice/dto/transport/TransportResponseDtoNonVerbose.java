package com.koliexpress.tripservice.dto.transport;

import com.koliexpress.tripservice.enums.TransportType;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportResponseDtoNonVerbose {
    private String url;
    private UUID id;
    private TransportType type;
}
