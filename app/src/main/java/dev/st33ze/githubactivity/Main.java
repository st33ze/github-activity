package dev.st33ze.githubactivity;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
  
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: github-activity <username>");
      return;
    }

    String username = args[0];
    GitHubClient client = new GitHubClient();
    ObjectMapper mapper = new ObjectMapper();


    try {
      String json = client.fetch(username);

      UserActivity[] activities = 
        mapper.readValue(json, UserActivity[].class);

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
