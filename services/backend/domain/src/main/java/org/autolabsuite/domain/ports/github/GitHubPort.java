package org.autolabsuite.domain.ports.github;

public interface GitHubPort {
  enum PRStatus { OPEN, CLOSED, MERGED, DRAFT, UNKNOWN }
  PRStatus getPullRequestStatus(String owner, String repo, int prNumber);
}
