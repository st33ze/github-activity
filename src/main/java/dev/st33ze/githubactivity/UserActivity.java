package dev.st33ze.githubactivity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserActivity(
  String type,
  Repo repo,
  String createdAt
) {}