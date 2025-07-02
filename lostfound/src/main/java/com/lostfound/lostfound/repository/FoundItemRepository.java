package com.lostfound.lostfound.repository;

import com.lostfound.lostfound.model.FoundItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {
}
