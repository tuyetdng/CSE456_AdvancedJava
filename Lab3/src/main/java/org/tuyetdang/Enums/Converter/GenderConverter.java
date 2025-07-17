package org.tuyetdang.Enums.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.tuyetdang.Enums.Gender;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn (Gender gender){
        return (gender != null) ? gender.getValue() : null;
    }

    @Override
    public Gender convertToEntityAttribute (String value){
        return (value != null) ? Gender.fromValue(value) : null;
    }
}
