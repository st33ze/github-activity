package dev.st33ze.githubactivity;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

class GitHubService {
  
  private final ObjectMapper mapper = new ObjectMapper();

  public UserActivity[] parse(String json) throws IOException {
    return mapper.readValue(json, UserActivity[].class);
  }

}
