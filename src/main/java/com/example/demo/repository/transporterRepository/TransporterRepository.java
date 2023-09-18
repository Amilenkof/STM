package com.example.demo.repository.transporterRepository;

import com.example.demo.model.Transporter;


public interface TransporterRepository {

    Transporter findByID(long id);

}
