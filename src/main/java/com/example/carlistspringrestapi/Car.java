package com.example.carlistspringrestapi;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Car {

   private Long id ;
   private String brand;
   private String model;
   private String color;

}
