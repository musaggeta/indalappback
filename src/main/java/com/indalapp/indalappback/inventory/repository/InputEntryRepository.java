package com.indalapp.indalappback.inventory.repository;

import com.indalapp.indalappback.inventory.entity.InputEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InputEntryRepository extends JpaRepository<InputEntry, Long> {
    List<InputEntry> findAllByOrderByEntryDateDesc();
}