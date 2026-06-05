package dev.st33ze.githubactivity;

public class Main {
  
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: github-activity <username>");
      return;
    }

    String username = args[0];

    System.out.println(username);
  }
}
