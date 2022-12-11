package com.musala.drones.data;

import lombok.Data;

import java.util.List;

@Data
public class LoadRequest {
    private List<String> medications;
}
