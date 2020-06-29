package com.tapumandal.ims.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tapumandal.ims.entity.Company;

import java.io.IOException;

public class CustomCompanySerializer extends StdSerializer<Company> {

    public CustomCompanySerializer() {
        this(null);
    }

    public CustomCompanySerializer(Class<Company> t) {
        super(t);
    }

    @Override
    public void serialize(Company company, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        company.setUsers(null);

        jsonGenerator.writeObject(company);
    }

}
