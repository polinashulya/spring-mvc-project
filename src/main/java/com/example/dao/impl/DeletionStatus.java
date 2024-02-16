package com.example.dao.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeletionStatus {
    NO_CONTENT(204),
    NOT_FOUND(404);

    private final int statusCode;

}