package com.example.librarymanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class LibraryDTO {
    private Long id;
    private String name;
    private List<Long> bookIds;
}