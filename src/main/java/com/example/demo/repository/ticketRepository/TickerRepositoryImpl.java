package com.example.demo.repository.ticketRepository;

import com.example.demo.model.Ticket;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.repository.UserRepository.UserRepositoryImpl;
import com.example.demo.repository.directionRepository.DirectionRepositoryImpl;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
public class TickerRepositoryImpl implements TicketRepository {
    private JdbcTemplate jdbcTemplate;
    private DirectionRepositoryImpl directionRepository;
    private UserRepositoryImpl userRepository;

    public TickerRepositoryImpl(JdbcTemplate jdbcTemplate, DirectionRepositoryImpl directionRepository, UserRepositoryImpl userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.directionRepository = directionRepository;
        this.userRepository = userRepository;
    }

    private final String FIND_TICKET_BY_ID = """
            SELECT id,direction_id,datetime,seat,price
            FROM ticket
            WHERE id=?
            """;

    @Override
    public Ticket findById(long id) {
     return    jdbcTemplate.query(FIND_TICKET_BY_ID, rs -> {
            Ticket ticket = new Ticket();
            ticket.builder()
                    .id(id)
                    .direction(directionRepository.findByID(rs.getLong(2)))
                    .dateTime(rs.getTimestamp(3).toLocalDateTime())
                    .seat(rs.getString(4))
                    .price(rs.getBigDecimal(5))
//                    .user()
                    .build();
            return ticket;
        });
    }
}
