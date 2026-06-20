package dev.st33ze.githubactivity;

class GitHubApiException  extends RuntimeException {
  
  GitHubApiException(String message) {
    super(message);
  }

  GitHubApiException(String message, Throwable cause) {
    super(message, cause);
  }
}
