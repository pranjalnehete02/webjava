package com.example.hotelroom;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAvailableRooms(@RequestParam(required = false, defaultValue = "true") boolean available) {
        return roomService.getAvailableRooms();
    }
}
