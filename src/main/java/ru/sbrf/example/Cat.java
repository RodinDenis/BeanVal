package ru.sbrf.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class Cat {

    @NotNull
    private String name;

    @NotNull
    @Size(min = 2, max = 14)
    private String age;

    @Min(2)
    private int owners;
}

