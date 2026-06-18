package dev.st33ze.githubactivity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

class GitHubServiceTest {
  
  private final GitHubService service = new GitHubService();

  @Test
  void parseEmptyArray() throws IOException {
    UserActivity[] result = service.parse("[]");

    assertEquals(0, result.length);
  }

  @Test 
  void parsePushEvent() throws IOException {
    String json = """
      [
        {
          "type": "PushEvent",
          "repo": {
            "name": "t3ster/RepoName"
          },
          "createdAt": "2025-06-01T12:00:00Z"
        }
      ]
      """;

    UserActivity[] result = service.parse(json);

    assertEquals(1, result.length);
    assertEquals("PushEvent", result[0].type());
    assertEquals("t3ster/RepoName", result[0].repo().name());
    assertEquals("2025-06-01T12:00:00Z", result[0].createdAt());
  }

  @Test
  void parseMultipleEvents() throws IOException {
    String json = """
      [
        {
          "type": "CreateEvent",
          "repo": {
            "name": "t3ster/NewRepo"
          },
          "createdAt": "2025-06-01T12:00:00Z"
        },
        {
          "type": "ForkEvent",
          "repo": {
            "name": "t3ster/RepoName"
          },
          "createdAt": "2025-06-01T12:00:00Z"
        }
      ]    
    """;

    UserActivity[] result = service.parse(json);

    assertEquals(2, result.length);
    
    assertEquals("CreateEvent", result[0].type());
    assertEquals("t3ster/NewRepo", result[0].repo().name());

    assertEquals("ForkEvent", result[1].type());
    assertEquals("t3ster/RepoName", result[1].repo().name());
  }


  @Test
  void parseEventWithUnusedFields() throws IOException {
    String json = """
      [
        {
          "type": "PushEvent",
          "repo": {
            "name": "t3ster/RepoName"
          },
          "createdAt": "2025-06-01T12:00:00Z",
          "extra_field": "ignored"
        }
      ]
      """;

      UserActivity[] result = service.parse(json);

      assertEquals(1, result.length);
  }

  @Test
  void invalidJsonThrowsException() {
    assertThrows(IOException.class, () -> service.parse("not json"));
  }
}
