package softwareengineering.manonisgaravattiferretti.cpmsServer.businessModel.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import softwareengineering.manonisgaravattiferretti.cpmsServer.businessModel.entities.Reservation;
import softwareengineering.manonisgaravattiferretti.cpmsServer.businessModel.repositories.aggregationResults.MaxSessionId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void maxSessionIdTest() {
        Reservation reservation1 = new Reservation();
        reservation1.setSessionId(50000L);
        Reservation reservation2 = new Reservation();
        reservation2.setSessionId(50004L);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
        MaxSessionId maxSessionIdAgg = reservationRepository.maxSessionId().getUniqueMappedResult();
        Assertions.assertNotNull(maxSessionIdAgg);
        Long maxSessionId = maxSessionIdAgg.getMaxSessionId();
        Assertions.assertEquals(50004L, maxSessionId);
        Optional<Reservation> reservation1BySessionId = reservationRepository.findReservationBySessionId(50000L);
        Optional<Reservation> reservation2BySessionId = reservationRepository.findReservationBySessionId(50004L);
        Assertions.assertTrue(reservation1BySessionId.isPresent());
        Assertions.assertTrue(reservation2BySessionId.isPresent());
        reservationRepository.delete(reservation1BySessionId.get());
        reservationRepository.delete(reservation2BySessionId.get());
    }
}