package com.chrisreal.dotuserlogtask.repository;

import com.chrisreal.dotuserlogtask.model.BlockedIpTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedIpRepository extends JpaRepository<BlockedIpTable, Long> {
}
