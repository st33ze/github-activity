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
    Output output = new Output();

    try {
      String json = client.fetch(username);
      UserActivity[] activities = service.parse(json);

      System.out.print(output.format(username, activities));
    } catch (IOException e) {
      System.err.println("IO error: " + e.getMessage());
    } catch (InterruptedException e) {
      System.err.println(("Request interrupted"));
      Thread.currentThread().interrupt();
    }

  }
}
