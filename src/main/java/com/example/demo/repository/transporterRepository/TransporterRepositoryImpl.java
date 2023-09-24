package com.example.demo.repository.transporterRepository;

import com.example.demo.model.Transporter;
import com.example.demo.repository.ticketRepository.TickerRepositoryImpl;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransporterRepositoryImpl implements TransporterRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String FIND_TRANSPORTER_BY_ID_SQL = """
            SELECT *
            FROM transporter
            WHERE id=?
            """;
    private final Logger logger = LoggerFactory.getLogger(TransporterRepositoryImpl.class);


    public TransporterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transporter findByID(long id) {
        logger.info("Invoke TransporterRepositoryImpl, method findById(id = {})",id);

        return (jdbcTemplate.queryForStream(FIND_TRANSPORTER_BY_ID_SQL, (rs, row) -> {
            new Transporter();
            return Transporter.builder()
                    .name(rs.getString("name"))
                    .phone(rs.getString("phone"))
                    .build();
        }, id)).findFirst().orElseThrow();

    }
}
