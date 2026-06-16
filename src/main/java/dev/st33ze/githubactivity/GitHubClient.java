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
    
    return response.body();
  }

  private URI createUri(String username) {
    return URI.create(
      "https://api.github.com/users/" +
      username +
      "/events"
    );
  }
}
