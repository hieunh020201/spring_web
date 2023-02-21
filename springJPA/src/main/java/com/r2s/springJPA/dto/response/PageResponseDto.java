package com.r2s.springJPA.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResponseDto implements Serializable {
    private int page;

    private int size;

    private int totalPages;

    private long totalRecord;

    private List<?> data;
}
