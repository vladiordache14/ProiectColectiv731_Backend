package com.example.proiectcolectiv731_be.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PhotoDto {
    @Id
    private Long imageId;
    private String photoUrl;
    private byte[] data;
}
