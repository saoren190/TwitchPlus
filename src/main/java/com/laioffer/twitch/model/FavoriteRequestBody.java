package com.laioffer.twitch.model;

import com.laioffer.twitch.db.entity.ItemEntity;

public record FavoriteRequestBody(
        ItemEntity favorite
) {
}
