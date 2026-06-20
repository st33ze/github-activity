package dev.st33ze.githubactivity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserActivity(
  String type,
  Repo repo,
  @JsonProperty("created_at") String createdAt
) {}