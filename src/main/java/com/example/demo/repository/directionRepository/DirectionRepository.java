package com.example.demo.repository.directionRepository;

import com.example.demo.model.Direction;

public interface DirectionRepository {
    Direction findByID(long id);
}
