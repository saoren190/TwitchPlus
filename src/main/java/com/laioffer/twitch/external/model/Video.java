package com.laioffer.twitch.external.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Video(
        String id,
        @JsonProperty("stream_id") String streamId,
        @JsonProperty("user_id") String userId,
        @JsonProperty("user_login") String userLogin,
        @JsonProperty("user_name") String userName,
        String title,
        String description,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("published_at") String publishedAt,
        String url,
        @JsonProperty("thumbnail_url") String thumbnailUrl,
        String viewable,
        @JsonProperty("view_count") Integer viewCount,
        String language,
        String type,
        String duration,
        @JsonProperty("muted_segments") List<Object> mutedSegments
) {
}
