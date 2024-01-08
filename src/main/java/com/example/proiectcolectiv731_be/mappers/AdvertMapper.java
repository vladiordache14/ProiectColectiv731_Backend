package com.example.proiectcolectiv731_be.mappers;

import com.example.proiectcolectiv731_be.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdvertMapper {
    public AdvertDto map(final Advert advert) {
        AdvertDto advertDto = new AdvertDto();
        advertDto.setId(advert.getAdvertId());
        advertDto.setName(advert.getName());
        advertDto.setDescription(advert.getDescription());
        advertDto.setPrice(advert.getPrice());
        advertDto.setSeller(new UserMapper().map(advert.getSeller()));
        advertDto.setIsPromoted(advert.getIsPromoted());
        advertDto.setIsBlocked(advert.getIsBlocked());
        advertDto.setIsActive(advert.getIsActive());

        List<PhotoDto> photoDtos = new ArrayList<>();
        for (Photo photo : advert.getImages()) {
            PhotoDto photoDto = new PhotoDto();
            photoDto.setImageId(photo.getImageId());
            photoDto.setPhotoUrl(photo.getUrl());
            photoDto.setData(photo.getData());
            photoDtos.add(photoDto);
        }
        advertDto.setPhotos(photoDtos);

        return advertDto;
    }


    public Advert dtoToAdvert(AdvertDto advertDto) {
        Advert a = new Advert();
        a.setAdvertId(advertDto.getId());
        a.setName(advertDto.getName());
        a.setDescription(advertDto.getDescription());
        a.setPrice(advertDto.getPrice());
        a.setImages(advertDto.getPhotoList());
        a.setIsPromoted(advertDto.getIsPromoted());
        a.setIsBlocked(advertDto.getIsBlocked());
        a.setSeller(advertDto.getUserId());
        a.setIsActive(advertDto.getIsActive());
        return a;
    }
}
