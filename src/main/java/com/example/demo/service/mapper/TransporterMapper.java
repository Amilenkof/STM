package com.example.demo.service.mapper;

import com.example.demo.model.DTO.TransporterDTOOut;
import com.example.demo.model.Transporter;
import com.example.demo.repository.UserRepository.UserRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransporterMapper {
    private final Logger logger = LoggerFactory.getLogger(TransporterMapper.class);

    public TransporterMapper() {
    }

    public TransporterDTOOut toDTOOut(Transporter transporter) {
        logger.info("Invoke TransporterMapper, method toDTOOut");

        return new TransporterDTOOut().builder()
                .name(transporter.getName())
                .phone(transporter.getPhone())
                .build();


    }
}
