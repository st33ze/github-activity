package dev.st33ze.githubactivity;

import java.io.IOException;

public class Main {
  
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: github-activity <username>");
      return;
    }

    String username = args[0];
    GitHubClient client = new GitHubClient();
    GitHubService service = new GitHubService();

    try {
      String json = client.fetch(username);

      UserActivity[] activities = service.parse(json);

      for (UserActivity a : activities) {
        System.out.println(a.type() + " -> " + a.repo().name());
      }
    } catch (IOException e) {
      System.err.println("IO error: " + e.getMessage());
    } catch (InterruptedException e) {
      System.err.println(("Request interrupted"));
      Thread.currentThread().interrupt();
    }

  }
}
