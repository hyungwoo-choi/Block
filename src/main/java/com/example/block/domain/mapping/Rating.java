package com.example.block.domain.mapping;

import com.example.block.domain.Contest;
import com.example.block.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Rating")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rater_id")
    private User rater;

    @ManyToOne
    @JoinColumn(name = "rated_id")
    private User rated;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @Column(nullable = false)
    private double score;
}
