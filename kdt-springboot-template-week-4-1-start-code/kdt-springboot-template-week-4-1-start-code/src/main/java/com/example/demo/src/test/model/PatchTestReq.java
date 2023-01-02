package com.example.demo.src.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchTestReq {
    private int id;
    private String memo;
}
