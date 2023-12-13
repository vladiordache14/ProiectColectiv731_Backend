package com.example.proiectcolectiv731_be.service;

import com.example.proiectcolectiv731_be.model.Advert;
import com.example.proiectcolectiv731_be.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    public List<Advert> getAllAdverts() {
        return this.advertRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Advert> getActiveAdverts() {
        return this.advertRepository.findByIsActive(true);
    }

<<<<<<< HEAD
    public Optional<Advert> getAdvertById(Long id) {
        return advertRepository.findById(id);
    }

    public boolean deleteAdvertById(Long id) {
        if (advertRepository.findById(id).isPresent()) {
            advertRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateExistingAdvert(Advert advert) {
        if (advertRepository.findById(advert.getAdvertId()).isPresent()) {
            advertRepository.save(advert);
            return true;
        }
        return false;
=======
    public Advert create(Advert advert) throws IllegalArgumentException {
        validateAdvert(advert);
        advertRepository.save(advert);
        return advert;
    }

    public void validateAdvert(Advert advert) {
        String description = advert.getDescription().toLowerCase();
        if (!description.contains("car") && !description.contains("vehicle") && !description.contains("auto")) {
            throw new IllegalArgumentException("The description must contain keywords: car/vehicle/auto.");
        }
>>>>>>> 70af8d6e194c860b3ac4ce31e249dabb3d494af4
    }
}
