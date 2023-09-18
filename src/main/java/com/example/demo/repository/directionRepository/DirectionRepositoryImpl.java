package com.example.demo.repository.directionRepository;

import com.example.demo.model.Direction;
import com.example.demo.repository.transporterRepository.TransporterRepositoryImpl;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
public class DirectionRepositoryImpl implements DirectionRepository {
    private JdbcTemplate jdbcTemplate;
    private TransporterRepositoryImpl transporterRepository;
    private final String FIND_DIRECTION_BY_ID = """
            SELECT id,departure_point,destination_point,transporter_id,duration
            FROM direction
            WHERE id=?
            """;

    @Override
    public Direction findByID(long id) {
        return jdbcTemplate.query(FIND_DIRECTION_BY_ID, rs -> {
                    Direction direction = new Direction();
                    direction.builder()
                            .id(id)
                            .departure_point(rs.getString(2))
                            .destination_point(rs.getString(3))
                            .transporter(transporterRepository.findByID(rs.getLong(4)))
                            .duration(rs.getLong(5))
                            .build();
                    return direction;
                }
        );
    }
}