package com.example.demo.repository.transporterRepository;

import com.example.demo.model.Transporter;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor


public class TransporterRepositoryImpl implements TransporterRepository {
    private JdbcTemplate jdbcTemplate;
    private final String FIND_TRANSPORTER_BY_ID = """
            SELECT id,name,phone
            FROM transporter
            WHERE id=?
            """;

    public TransporterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transporter findByID(long id) {
        return jdbcTemplate.query(FIND_TRANSPORTER_BY_ID, rs -> {
                    Transporter transporter = new Transporter().builder()
                            .name(rs.getString(2))
                            .phone(rs.getString(3)).build();
                    return transporter;
                }
        );
    }
}
