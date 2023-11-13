package com.food.marcosfood.api.exceptionhander;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problema {

    private Integer status;
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Object> objects;
    private List<Object> object = new ArrayList<>();

    @Getter
    @Builder
    public static class Object{
        private String name;
        private String userMensager;
    }


}
