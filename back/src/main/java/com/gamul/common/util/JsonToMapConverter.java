package com.gamul.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class JsonToMapConverter
        implements AttributeConverter<String, Map<String, Object>>
{
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> convertToDatabaseColumn(String attribute)
    {
        if (attribute == null) {
            return new HashMap<>();
        }
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(attribute, HashMap.class);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return new HashMap<>();
    }

    @Override
    public String convertToEntityAttribute(Map<String, Object> dbData)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(dbData);
        }
        catch (JsonProcessingException e)
        {
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
