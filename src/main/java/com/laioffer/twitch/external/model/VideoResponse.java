package com.laioffer.twitch.external.model;


import java.util.List;

public record VideoResponse(
        List<Video> data
) {
}
