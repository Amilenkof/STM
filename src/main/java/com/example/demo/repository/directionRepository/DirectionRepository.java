package com.example.demo.repository.directionRepository;

import com.example.demo.model.Direction;

import java.util.Optional;

public interface DirectionRepository {



    Direction findById(long id);
}
