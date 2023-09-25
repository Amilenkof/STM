package com.example.demo.repository.ticketRepository;

import com.example.demo.exception.CurrentTicketHasBeenSold;
import com.example.demo.exception.ImpossibleCreateTicketException;
import com.example.demo.exception.TicketNotFoundException;
import com.example.demo.model.Ticket;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.repository.directionRepository.DirectionRepositoryImpl;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class TickerRepositoryImpl implements TicketRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DirectionRepositoryImpl directionRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(TickerRepositoryImpl.class);


    private final String ADD_NEW_TICKET = """
            INSERT INTO ticket (direction_id, datetime,seat,price)
            VALUES (?,?,?,?);
            """;
    private final String FIND_TICKET_BY_ID_SQL = """
            SELECT id,direction_id,datetime,seat,price,status,user_id
            FROM ticket
            WHERE id=?
            """;
    private final String SQL = """
            SELECT *
            FROM ticket ti
            JOIN direction d ON ti.direction_id = d.id
            JOIN transporter t ON t.id = d.transporter_id 
            WHERE ti.status= 'FREE' """;

    private final String UDPATE_STATUS_SQL = """
            UPDATE ticket
            SET status = 'SOLD'
            WHERE id = ? AND status = 'FREE';
                      """;


    public TickerRepositoryImpl(JdbcTemplate jdbcTemplate, DirectionRepositoryImpl directionRepository, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.directionRepository = directionRepository;
        this.userRepository = userRepository;
    }

    @Transactional()
    @Override
    public Ticket findById(long id) {
        logger.info("Invoke TicketRepositoryImpl, method findById");

        return (jdbcTemplate.queryForStream(FIND_TICKET_BY_ID_SQL, (rs, row) -> {
            new Ticket();
            return Ticket.builder()
                    .id(id)
                    .user(null)//todo в конструкторе указать дефолтный статус "FREE" или сделать статик переменную  и присваивать ее в констукторе
                    .price(rs.getBigDecimal("price"))
                    .seat(rs.getString("seat"))
                    .direction(directionRepository.findById(rs.getLong("direction_id")))
                    .status(rs.getString("status"))
                    .dateTime((rs.getTimestamp("datetime")).toLocalDateTime())
                    .build();
        }, id))
                .findFirst()
                .orElseThrow(() -> new TicketNotFoundException("The specified ticket was not found"));

    }

    @Transactional()
    @Override
    public Ticket greateNewTicket(Ticket ticket) {
        logger.info("Invoke TicketRepositoryImpl, method greateNewTicket");

        int update = jdbcTemplate.update(ADD_NEW_TICKET,
                ticket.getDirection().getId(),
                ticket.getDateTime(),
                ticket.getSeat(),
                ticket.getPrice());
        if (update != 0) {
            return ticket;
        } else throw new ImpossibleCreateTicketException("It is not possible to create the specified ticket");


    }


    @Transactional()
    @Override
    public List<Ticket> getAllTickets(int page, int size, String dateTime, String destinationPoint, String departurePoint, String transporterName) {
        logger.info("Invoke TicketRepositoryImpl, method getAllTickets");

        String SQL_Query = createSQLQuery(page, size, dateTime, destinationPoint, departurePoint, transporterName);
        return jdbcTemplate.queryForStream(SQL_Query, (rs, row) -> {
            new Ticket();
            return Ticket.builder()
                    .status(rs.getString("status"))
                    .price(rs.getBigDecimal("price"))
                    .seat(rs.getString("seat"))
                    .direction(directionRepository.findById(rs.getLong("direction_id")))
                    .id(rs.getLong("id"))
                    .user(userRepository.findById(rs.getLong("user_id")))
                    .dateTime(rs.getTimestamp("datetime").toLocalDateTime())
                    .build();

        }).toList();

    }

    private String createSQLQuery(int page, int size, String dateTime, String destinationPoint, String departurePoint, String transporterName) {
        StringBuilder stringBuilder = new StringBuilder(SQL);
        logger.info("Invoke TicketRepositoryImpl, method createSQLQuery");
        if (dateTime != null) {
            stringBuilder.append(" AND  ti.datetime= ");
            stringBuilder.append(dateTime);
        }

        if (departurePoint != null) {
            stringBuilder.append(" AND  d.departure_point like ");
            stringBuilder.append(" '" + departurePoint + "' ");//todo плохой вариант куча строк просто так создается
        }
        if (destinationPoint != null) {
            stringBuilder.append(" AND  d.destination_point like ");
            stringBuilder.append(" '" + destinationPoint + "' ");
        }
        if (transporterName != null) {
            stringBuilder.append(" AND  t.name like ");
            stringBuilder.append(" '" + transporterName + "' ");
        }
        if (page > 0) {
            stringBuilder.append(" OFFSET " + page);
        }
        if (size > 0) {
            stringBuilder.append(" LIMIT " + size);
        }
        logger.info("\n Final SQL Query ={}", stringBuilder.toString());
        return stringBuilder.toString();
    }

    @Transactional()
    public int updateStatus(long id) {
        logger.info("Invoke TicketRepositoryImpl, method updateStatus ,id = {}", id);
//        Ticket ticket = findById(id);
        return jdbcTemplate.update(UDPATE_STATUS_SQL, id);//todo не работает


    }

// private final String UDPATE_STATUS_SQL = """
//            UPDATE ticket
//            SET status = ?
//            WHERE id = ? AND status = 'FREE ';
//                      """;
}
