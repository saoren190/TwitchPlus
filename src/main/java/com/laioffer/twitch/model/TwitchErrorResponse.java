package com.laioffer.twitch.model;

public record TwitchErrorResponse(
        String message,
        String error,
        String details
) {
}
