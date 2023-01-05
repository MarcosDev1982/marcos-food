package com.food.marcosfood.ipi.exceptionhander;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problema {

    private String status;
    private String type;
    private String title;
    private String detail;


}
