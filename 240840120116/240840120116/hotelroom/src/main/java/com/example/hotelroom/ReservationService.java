package com.example.hotelroom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        Long roomId = reservation.getRoom().getId();
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        if (!room.isAvailable()) {
            throw new IllegalStateException("Room is not available");
        }

        // Mark room as unavailable
        room.setAvailable(false);
        roomRepository.save(room);

        // Save reservation
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            Room room = reservation.getRoom();
            room.setAvailable(true); // Mark room as available again
            roomRepository.save(room);
            reservationRepository.delete(reservation);
        } else {
            throw new IllegalStateException("Reservation not found");
        }
    }
}
