package com.example.service.dto;

import com.example.service.dto.core.AbstractBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class PageableDto<D extends AbstractBaseDto> {

    private List<D> elements;

    private Integer totalSize;
}
