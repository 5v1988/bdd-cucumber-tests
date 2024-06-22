package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface Mappers {

  default <T> T fromJsonString(String jsonString, Class<T> clazz){
    try {
      return new ObjectMapper().readValue(jsonString,  clazz);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
