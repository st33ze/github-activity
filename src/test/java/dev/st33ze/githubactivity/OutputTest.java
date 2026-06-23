package dev.st33ze.githubactivity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputTest {
  
  private final Output output = new Output();

  @Test
  void formatPushEvent() {
    String expected = expect("t3ster", "[2026-01-01 10:00] Pushed commits to t3ster/repoName");

    assertEquals(expected, format(activity("PushEvent")));
  }

  @Test
  void formatCreateEvent() {
    String expected = expect("t3ster", "[2026-01-01 10:00] Created a new repository t3ster/repoName");
    
    assertEquals(expected, format(activity("CreateEvent")));
  }

  @Test
  void formatForkEvent() {
    String expected = expect("t3ster", "[2026-01-01 10:00] Forked repository t3ster/repoName");

    assertEquals(expected, format(activity("ForkEvent")));
  }

  @Test
  void formatWatchEvent() {
    String expected = expect("t3ster", "[2026-01-01 10:00] Starred t3ster/repoName");

    assertEquals(expected, format(activity("WatchEvent")));
  }

  @Test void formatIssuesEvent() {
    String expected = expect("t3ster", "[2026-01-01 10:00] Opened a new issue in t3ster/repoName");

    assertEquals(expected, format(activity("IssuesEvent")));
  }

  @Test
  void formatEmptyActivitesArray() {
    String expected = "No supported activity found from t3ster";

    assertEquals(expected, format());
  }

  @Test
  void formatNotTrackedActivities() {
    String expected = "No supported activity found from t3ster";

    assertEquals(expected, format(activity("SomeOtherEvent")));
  }

  @Test
  void ignoreUnsupportedEvents() {
    String expected = expect("t3ster", "[2026-01-01 10:00] Pushed commits to t3ster/repoName");

    assertEquals(
      expected,
      format(activity("UnsupportedEvent"), activity("PushEvent"))
    );
  }

  @Test
  void ignoreInvalidDate() {
    String expected = expect("t3ster", "- Opened a new issue in t3ster/repoName");

    assertEquals(expected, format(activity("IssuesEvent", "t3ster/repoName", "date")));
  }

  @Test
  void ignoreEmptyDate() {
    String expected = expect("t3ster", "- Starred t3ster/repoName");

    assertEquals(expected, format(activity("WatchEvent", "t3ster/repoName", "")));
  }

  @Test
  void ignoreNullDate() {
    String expected = expect("t3ster", "- Created a new repository t3ster/repoName");

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
