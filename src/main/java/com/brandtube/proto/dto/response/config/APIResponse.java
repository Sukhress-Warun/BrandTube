package com.brandtube.proto.dto.response.config;

import com.brandtube.proto.exceptions.CustomExceptions;
import com.brandtube.proto.exceptions.ServerException;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;
import com.google.common.base.CaseFormat;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class APIResponse<T> {

    private HttpStatus code;
    @JsonIgnore
    private String message = null;
    @JsonIgnore
    private T data;

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, Object> processedAny = null;
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean isProcessed = false;

    private Object getReturnData(APIResponseConfig ant, Class<?> dataClass) {
        Object returnData = data;
        if (ant.isCollection()) {
            APIResponseCollectionConfig collectionConfig = dataClass.getAnnotation(APIResponseCollectionConfig.class);
            if (collectionConfig == null) {
                throw new ServerException(APIResponseCollectionConfig.class.getSimpleName() + " annotation is missing on class " + dataClass.getSimpleName());
            }
            String collectionFieldName = collectionConfig.fieldName();
            Field field;
            try {
                field = dataClass.getDeclaredField(collectionFieldName);
                if(!Modifier.isPublic(field.getModifiers())){
                    throw new ServerException("Field " + collectionFieldName + " is not public in class " + dataClass.getSimpleName());
                }
                returnData = field.get(data);

            } catch (NoSuchFieldException e) {
                throw new ServerException("Field " + collectionFieldName + " not found in class " + dataClass.getSimpleName());
            } catch (IllegalAccessException e) {
                throw new ServerException("Cannot access field " + collectionFieldName + " in class " + dataClass.getSimpleName());
            }

        }
        return returnData;
    }


    public void processData() {

        Map <String, Object> map = new HashMap<>();

        if (message != null) {
            map.put("message", message);
        }

        if(data == null){
            this.processedAny = map;
            this.isProcessed = true;
            return;
        }

        Class<?> dataClass = data.getClass();
        APIResponseConfig ant = dataClass.getAnnotation(APIResponseConfig.class);

        if (ant == null) {
            throw new ServerException(APIResponseConfig.class.getSimpleName() + " annotation is missing on class " + data.getClass().getSimpleName());
        }

        String key;
        if (ant.classNameToSnakeCase()) {
            key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, dataClass.getSimpleName());
        }
        else {
            key = ant.name();
        }

        Object returnData = getReturnData(ant, dataClass);

        map.put(key, returnData);
        this.processedAny = map;
        this.isProcessed = true;
    }

    @JsonAnyGetter
    public Map<String, Object> any(){
        if(!isProcessed){
            processData();
        }
        return this.processedAny;
    }
}