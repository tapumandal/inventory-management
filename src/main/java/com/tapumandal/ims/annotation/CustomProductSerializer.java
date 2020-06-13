package com.tapumandal.ims.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tapumandal.ims.entity.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomProductSerializer extends StdSerializer<List<Product>> {

    public CustomProductSerializer() {
        this(null);
    }

    public CustomProductSerializer(Class<List<Product>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Product> measurement,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Product> pro = new ArrayList<>();
        for (Product s : measurement) {
            s.setMeasurement(null);
            pro.add(s);
        }
        generator.writeObject(pro);
    }
}
