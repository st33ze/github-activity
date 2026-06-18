package dev.st33ze.githubactivity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OutputTest {
  
  private final Output output = new Output();

  @Test
  void formatPushEvent() {
    UserActivity[] activities = {
      activity("PushEvent", "t3ster/newRepo")
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Pushed commits to t3ster/newRepo"));
  }

  @Test
  void formatCreateEvent() {
    UserActivity[] activities = {
      activity("CreateEvent", "t3ster/newRepo")
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Created a new repository t3ster/newRepo"));
  }

  @Test
  void formatForkEvent() {
    UserActivity[] activities = {
      activity("ForkEvent", "t3ster/ForkedRepo")
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Forked repository t3ster/ForkedRepo"));
  }

  @Test
  void formatWatchEvent() {
    UserActivity[] activities = {
      activity("WatchEvent", "t3ster/StarredRepo")
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Starred t3ster/StarredRepo"));
  }

  @Test
  void formatIssuesEvent() {
    UserActivity[] activities = {
      activity("IssuesEvent", "t3ster/IssuedRepo")
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Opened a new issue in t3ster/IssuedRepo"));
  }

  @Test
  void formatEmptyActivitiesArray() {
    UserActivity[] activities = {};

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("No supported activity found from t3ster"));
  }

  @Test
  void formatNotTrackedActivities() {
    UserActivity[] activities = {
      activity("SomeOtherEvent", "t3ster/repoName")
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("No supported activity found from t3ster"));
  }

  @Test
  void ignoreUnsupportedEvents() {
    UserActivity[] activities = {
      activity("PushEvent", "t3ster/repoName"),
      activity("UnsupportedEvent", "t3ster/repoName")
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Pushed commits to t3ster/repoName"));
  }

  private UserActivity activity(String type, String repo) {
    return new UserActivity(
      type,
      new Repo(repo),
      "date"
    );
  }

}
