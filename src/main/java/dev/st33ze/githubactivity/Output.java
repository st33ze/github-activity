package dev.st33ze.githubactivity;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Output {
  
  String format(String username, UserActivity[] activities) {
    StringBuilder output = new StringBuilder();
    
    for (UserActivity activity: activities) {
      String line = formatLine(activity);

      if (!line.isEmpty()) 
        output.append(line).append("\n");
    }

    if (output.length() == 0)
      return "No supported activity found from " + username;

    return "Recent GitHub activity from " + username + "\n\n" + output;
  }

  private String formatLine(UserActivity activity) {
    String formatedActivity = formatActivity(activity);

    if (formatedActivity.isEmpty()) return "";

    String date = formatDate(activity.createdAt());

    if (date.isEmpty()) return "- " + formatedActivity;
    return "[" + date + "] " + formatedActivity;
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

  private String formatDate(String isoDate) {
    if (isoDate == null || isoDate.isBlank()) return "";

    try {
      return OffsetDateTime.parse(isoDate)
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    } catch (DateTimeParseException e) {
      return "";
    }
  }
}
