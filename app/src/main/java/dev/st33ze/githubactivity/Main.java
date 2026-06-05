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

    try {
      String json = client.fetch(username);
      System.out.println(json);
    } catch (IOException e) {
      System.err.println("Network error: " + e.getMessage());
    } catch (InterruptedException e) {
      System.err.println(("Request interrupted"));
      Thread.currentThread().interrupt();
    }

  }
}
