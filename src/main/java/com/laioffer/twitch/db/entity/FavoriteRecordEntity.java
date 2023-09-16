package com.laioffer.twitch.db.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("favorite_records")
public record FavoriteRecordEntity(
        @Id Long id,
        Long userId,
        Long itemId,
        Instant createdAt
) {
}
