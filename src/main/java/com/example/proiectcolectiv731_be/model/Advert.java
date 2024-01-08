package com.example.proiectcolectiv731_be.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="adverts")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advertId;

    @Column(name = "name")
    @NotEmpty(message = "Name must not be empty!")
    @NonNull
    @Size(max = 20, message = "Name must not exceed 20 characters!")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "Description must not be empty!")
    @NonNull
    @Size(max = 300, message = "Description must not exceed 300 characters!")
    private String description;

    @Column(name = "price")
    @NotEmpty(message = "Price must not be empty!")
    private Float price;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Size(min = 1, max = 3, message = "An advert must have between 1 and 3 images")
    private List<Photo> images;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(name = "is_promoted", nullable = false)
    private Boolean isPromoted;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
