package com.brandtube.proto.dto.response.config;

import com.brandtube.proto.exceptions.CustomExceptions;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;
import com.google.common.base.CaseFormat;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class APIResponse<T> {

    @JsonIgnore
    String message = null;
    HttpStatus code;
    @JsonIgnore
    T data;

    private Object getReturnData(APIResponseConfig ant, Class<?> dataClass) throws NoSuchFieldException, IllegalAccessException {
        Object returnData = data;
        if (ant.isCollection()) {
            APIResponseCollectionConfig collectionConfig = dataClass.getAnnotation(APIResponseCollectionConfig.class);
            if (collectionConfig == null) {
                throw new CustomExceptions("APIResponseCollectionConfig annotation is missing on class " + dataClass.getSimpleName());
            }
            String collectionFieldName = collectionConfig.fieldName();
            Field field = dataClass.getField(collectionFieldName);
            returnData = field.get(data);
        }
        return returnData;
    }

    @JsonAnyGetter
    public Map<String, Object> any() throws NoSuchFieldException, IllegalAccessException {

        Map <String, Object> map = new HashMap<>();

        if (message != null) {
            map.put("message", message);
        }

        if(data == null){
            return map;
        }

        Class<?> dataClass = data.getClass();
        APIResponseConfig ant = dataClass.getAnnotation(APIResponseConfig.class);

        if (ant == null) {
            throw new CustomExceptions("APIResponseConfig annotation is missing on class " + data.getClass().getSimpleName());
        }

        String key = ant.name();
        if (ant.classNameToSnakeCase()) {
            key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, dataClass.getSimpleName());
        }

        Object returnData = getReturnData(ant, dataClass);

        map.put(key, returnData);
        return map;
    }
}