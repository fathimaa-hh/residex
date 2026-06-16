package com.residex.room.repository;

import com.residex.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository
        extends JpaRepository<Room, Long> {
}