package com.example.demo.repository.directionRepository;

import com.example.demo.exception.DirectionNotFoundException;
import com.example.demo.model.Direction;
import com.example.demo.repository.ticketRepository.TickerRepositoryImpl;
import com.example.demo.repository.transporterRepository.TransporterRepositoryImpl;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public class DirectionRepositoryImpl implements DirectionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TransporterRepositoryImpl transporterRepository;
    private final Logger logger = LoggerFactory.getLogger(DirectionRepositoryImpl.class);


    public DirectionRepositoryImpl(JdbcTemplate jdbcTemplate, TransporterRepositoryImpl transporterRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.transporterRepository = transporterRepository;
    }

    private final String FIND_DIRECTION_BY_ID_SQL = """
            SELECT *
            FROM direction
            WHERE id=?;
            """;


    @Transactional()
    @Override
    public Direction findById(long id) {
        logger.info("Invoke DirectionRepositoryImpl, method findById");

        return (jdbcTemplate.queryForStream(FIND_DIRECTION_BY_ID_SQL, (rs, row) -> {
            new Direction();
            return Direction.builder()
                    .id(id)
                    .departure_point(rs.getString("departure_point"))
                    .destination_point(rs.getString("destination_point"))
                    .transporter(transporterRepository.findByID(rs.getLong("id")))
                    .duration(rs.getLong("duration"))
                    .build();
        }, id)).findFirst().orElseThrow(() -> new DirectionNotFoundException("It is not possible to create the specified direction"));

    }
}