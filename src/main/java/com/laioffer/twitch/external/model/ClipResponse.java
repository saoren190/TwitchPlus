package com.laioffer.twitch.external.model;


import java.util.List;

public record ClipResponse(
        List<Clip> data
) {
}
