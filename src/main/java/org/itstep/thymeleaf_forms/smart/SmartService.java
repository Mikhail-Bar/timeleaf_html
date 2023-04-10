package org.itstep.thymeleaf_forms.smart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartService {
    @Autowired
    SmartRepo smartRepo;

    public List<Smart> findAll(){
        return smartRepo.findAll();
    }

    public Smart save(Smart smart){
        return smartRepo.save(smart);
    }

    public Smart findById(Long id){
        return smartRepo.findById(id).get();
    }

    public void deleteById(Long id){
        smartRepo.deleteById(id);
    }
}
