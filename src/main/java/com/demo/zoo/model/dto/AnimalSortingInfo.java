package com.demo.zoo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalSortingInfo {
    private String sortingFieldName;
    private String sortingFieldOrder;
}
