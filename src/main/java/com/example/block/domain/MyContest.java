package com.example.block.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "mycontest")
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class MyContest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // User 엔티티의 id와 매핑
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id") // Contest 엔티티의 id와 매핑
    private Contest contest;
}