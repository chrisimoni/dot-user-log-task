package com.chrisreal.dotuserlogtask.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BLOCKED_IP_TABLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockedIpTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String ip;
    private int requestNumber;
    private String comment ;
}
