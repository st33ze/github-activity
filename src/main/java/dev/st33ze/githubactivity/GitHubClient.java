package dev.st33ze.githubactivity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class GitHubClient {
  
  private final HttpClient client = HttpClient.newHttpClient();

  String fetch(String username) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
      .uri(createUri(username))
      .GET()
      .build();

    HttpResponse<String> response = 
      client.send(request, HttpResponse.BodyHandlers.ofString());
    
    return handleResponse(response, username);
  }

  private URI createUri(String username) {
    return URI.create(
      "https://api.github.com/users/" +
      username +
      "/events"
    );
  }

  private String handleResponse(HttpResponse<String> response, String username) {
    int status = response.statusCode();

    if (status == 200) return response.body();

    if (status == 404)
      throw new GitHubApiException("User not found: " + username);

    if (status == 403)
      throw new GitHubApiException("Rate limit exceeded or access forbidden");

    throw new GitHubApiException("GitHub API error: HTTP " + status);
  }
}
