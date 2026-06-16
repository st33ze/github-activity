package dev.st33ze.githubactivity;

class Output {
  
  String format(String username, UserActivity[] activities) {
    StringBuilder output = new StringBuilder();
    
    for (UserActivity activity: activities) {
      String line = formatActivity(activity);

      if (!line.isEmpty()) 
        output.append("- ").append(line).append("\n");
    }

    if (output.isEmpty())
      return "No supported activity found from " + username;

    return "Recent GitHub activity from " + username + "\n\n" + output;
  }

  private String formatActivity(UserActivity activity) {
    return switch (activity.type()) {
        case "PushEvent"-> "Pushed commits to " + activity.repo().name();
        case "CreateEvent" -> "Created a new repository " + activity.repo().name(); 
        case "ForkEvent" -> "Forked repository " + activity.repo().name();
        case "WatchEvent" -> "Starred " + activity.repo().name();
        case "IssuesEvent" -> "Opened a new issue in " + activity.repo().name();
        default -> "";
      };
  }
}
