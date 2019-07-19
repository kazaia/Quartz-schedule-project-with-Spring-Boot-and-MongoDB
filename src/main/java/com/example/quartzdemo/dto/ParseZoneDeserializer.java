package com.example.quartzdemo.dto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

class ParseZoneDeserializer extends StdDeserializer<ZoneId> {
    public ParseZoneDeserializer() {
        super(ZoneId.class);
    }

    @Override
    public ZoneId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return ZoneId.of(p.getValueAsString()); // or overloaded with an appropriate format
    }
}