package com.tapumandal.ims.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tapumandal.ims.entity.Measurement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomMeasurementSerializer extends StdSerializer<List<Measurement>> {

    public CustomMeasurementSerializer() {
        this(null);
    }

    public CustomMeasurementSerializer(Class<List<Measurement>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Measurement> measurement,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Measurement> measur = new ArrayList<>();
        for (Measurement s : measurement) {
            s.setProducts(null);
            measur.add(s);
        }
        generator.writeObject(measur);
    }
}
