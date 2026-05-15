package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
