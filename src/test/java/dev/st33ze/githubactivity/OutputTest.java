package dev.st33ze.githubactivity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OutputTest {
  
  @Test
  void formatPushEvent() {
    Output output = new Output();
    
    UserActivity[] activities = {
      new UserActivity(
        "PushEvent",
        new Repo("t3ster/newRepo"),
        "date"
      )
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Pushed commits to t3ster/newRepo"));
  }

  @Test
  void formatCreateEvent() {
    Output output = new Output();

    UserActivity[] activities = {
      new UserActivity(
        "CreateEvent",
        new Repo("t3ster/newRepo"),
        "date"
      )
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Created a new repository t3ster/newRepo"));
  }

  @Test
  void formatForkEvent() {
    Output output = new Output();

    UserActivity[] activities = {
      new UserActivity(
        "ForkEvent",
        new Repo("t3ster/ForkedRepo"),
        "date"
      )
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Forked repository t3ster/ForkedRepo"));
  }

  @Test
  void formatWatchEvent() {
    Output output = new Output();

    UserActivity[] activities = {
      new UserActivity(
        "WatchEvent",
        new Repo("t3ster/StarredRepo"),
        "date"
      )
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Starred t3ster/StarredRepo"));
  }

  @Test
  void formatIssuesEvent() {
    Output output = new Output();

    UserActivity[] activities = {
      new UserActivity(
        "IssuesEvent",
        new Repo("t3ster/IssuedRepo"),
        "date"
      )
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Opened a new issue in t3ster/IssuedRepo"));
  }

  @Test
  void formatEmptyActivitiesArray() {
    Output output = new Output();

    UserActivity[] activities = {};

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("No supported activity found from t3ster"));
  }

  @Test
  void formatNotTrackedActivities() {
    Output output = new Output();

    UserActivity[] activities = {
      new UserActivity(
        "SomeOtherEvent",
        new Repo("t3ster/RepoName"),
        "date"
      )
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("No supported activity found from t3ster"));
  }

  @Test
  void ignoreUnsupportedEvents() {
    Output output = new Output();

    UserActivity[] activities = {
      new UserActivity(
        "PushEvent",
        new Repo("t3ster/RepoName"),
        "date"
      ),
      new UserActivity(
        "UnsupportedEvent",
        new Repo("t3ster/repoName"),
        "date"
      )
    };

    String result = output.format("t3ster", activities);

    assertTrue(result.contains("Pushed commits to t3ster/RepoName"));
  }

}
