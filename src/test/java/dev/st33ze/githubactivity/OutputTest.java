package dev.st33ze.githubactivity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputTest {

  private static final String USERNAME = "t3ster";
  private final Output output = new Output();

  @ParameterizedTest
  @CsvSource({
    "PushEvent, Pushed commits to t3ster/repoName",
    "CreateEvent, Created a new repository t3ster/repoName",
    "ForkEvent, Forked repository t3ster/repoName",
    "WatchEvent, Starred t3ster/repoName",
    "IssuesEvent, Opened a new issue in t3ster/repoName"
  })
  void formatSupportedEvents(String eventType, String activityText) {
    String expected = expect(USERNAME, "[2026-01-01 10:00] " + activityText);

    assertEquals(expected, format(activity(eventType)));
  }

  @Test
  void formatEmptyActivitesArray() {
    String expected = "No supported activity found from t3ster\n";

    assertEquals(expected, format());
  }

  @Test
  void formatNotTrackedActivities() {
    String expected = "No supported activity found from t3ster\n";

    assertEquals(expected, format(activity("SomeOtherEvent")));
  }

  @Test
  void ignoreUnsupportedEvents() {
    String expected = expect(USERNAME, "[2026-01-01 10:00] Pushed commits to t3ster/repoName");

    assertEquals(
      expected,
      format(activity("UnsupportedEvent"), activity("PushEvent"))
    );
  }

  @Test
  void ignoreInvalidDate() {
    String expected = expect(USERNAME, "- Opened a new issue in t3ster/repoName");

    assertEquals(expected, format(activity("IssuesEvent", "t3ster/repoName", "date")));
  }

  @Test
  void ignoreEmptyDate() {
    String expected = expect(USERNAME, "- Starred t3ster/repoName");

    assertEquals(expected, format(activity("WatchEvent", "t3ster/repoName", "")));
  }

  @Test
  void ignoreNullDate() {
    String expected = expect(USERNAME, "- Created a new repository t3ster/repoName");

    assertEquals(expected, format(activity("CreateEvent", "t3ster/repoName", null)));
  }

  private String format(UserActivity ...activities) {
    return output.format("t3ster", activities);
  }

  private String expect(String username, String line) {
    return "Recent GitHub activity from " + username + "\n\n" + line + "\n";
  }

  private UserActivity activity(String type, String repo, String date) {
    return new UserActivity(
      type,
      new Repo(repo),
      date
    );
  }

  private UserActivity activity(String type, String repo) {
    return activity(type, repo, "2026-01-01T10:00:00Z");
  }

  private UserActivity activity(String type) {
    return activity(type, "t3ster/repoName");
  }

}
