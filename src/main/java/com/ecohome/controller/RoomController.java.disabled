package com.ecohome.controller;

import com.ecohome.dto.RoomDTO;
import com.ecohome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RoomDTO>> getRoomsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(roomService.getRoomsByUser(userId));
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(
            @RequestParam String name,
            @RequestParam Long userId) {
        RoomDTO created = roomService.createRoom(name, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(
            @PathVariable Long id,
            @RequestParam String name) {
        return ResponseEntity.ok(roomService.updateRoom(id, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
