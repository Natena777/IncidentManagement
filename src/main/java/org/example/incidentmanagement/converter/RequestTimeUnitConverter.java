package org.example.incidentmanagement.converter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.incidentmanagement.enums.RequestTimeUnitEnums;

@Converter
public class RequestTimeUnitConverter implements AttributeConverter<RequestTimeUnitEnums, String> {

    @Override
    public String convertToDatabaseColumn(RequestTimeUnitEnums attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDisplayValue();
    }

    @Override
    public RequestTimeUnitEnums convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return RequestTimeUnitEnums.fromString(dbData);
    }
}