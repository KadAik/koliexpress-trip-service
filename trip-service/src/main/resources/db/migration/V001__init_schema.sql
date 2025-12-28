CREATE TABLE transports
(
    id                     UUID                        NOT NULL,
    transport_type         VARCHAR(31)                 NOT NULL,
    provider_name          VARCHAR(255),
    reference_number       VARCHAR(255),
    verification_status    VARCHAR(255),
    additional_info        TEXT,
    created_at             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    vehicle_make           VARCHAR(255),
    vehicle_model          VARCHAR(255),
    vehicle_year           INTEGER,
    license_plate          VARCHAR(255),
    vehicle_color          VARCHAR(255),
    flight_number          VARCHAR(255),
    airline_code           VARCHAR(255),
    booking_reference      VARCHAR(255),
    departure_airport_code VARCHAR(10),
    arrival_airport_code   VARCHAR(10),
    aircraft_type          VARCHAR(255),
    bus_company            VARCHAR(255),
    bus_number             VARCHAR(255),
    departure_station      VARCHAR(255),
    arrival_station        VARCHAR(255),
    CONSTRAINT pk_transports PRIMARY KEY (id)
);

CREATE TABLE travelers
(
    id UUID NOT NULL,
    CONSTRAINT pk_travelers PRIMARY KEY (id)
);

CREATE TABLE trips
(
    id                    UUID                        NOT NULL,
    traveler_id           UUID                        NOT NULL,
    departure_date        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    arrival_date          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    available_weight      DECIMAL                     NOT NULL,
    price_per_kg          DECIMAL                     NOT NULL,
    price_asked           DECIMAL                     NOT NULL,
    transport_type        VARCHAR(255)                NOT NULL,
    transport_id          UUID,
    status                VARCHAR(255)                NOT NULL,
    notice                TEXT,
    created_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    origin_name           VARCHAR(255),
    origin_city           VARCHAR(255),
    origin_country        VARCHAR(255),
    origin_latitude       DECIMAL,
    origin_longitude      DECIMAL,
    destination_name      VARCHAR(255),
    destination_city      VARCHAR(255),
    destination_country   VARCHAR(255),
    destination_latitude  DECIMAL,
    destination_longitude DECIMAL,
    CONSTRAINT pk_trips PRIMARY KEY (id)
);

ALTER TABLE trips
    ADD CONSTRAINT uc_trips_transport UNIQUE (transport_id);

ALTER TABLE trips
    ADD CONSTRAINT FK_TRIPS_ON_TRANSPORT FOREIGN KEY (transport_id) REFERENCES transports (id);

ALTER TABLE trips
    ADD CONSTRAINT FK_TRIPS_ON_TRAVELER FOREIGN KEY (traveler_id) REFERENCES travelers (id);