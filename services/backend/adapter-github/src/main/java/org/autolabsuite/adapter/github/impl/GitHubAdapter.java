package org.autolabsuite.adapter.github.impl;

import org.autolabsuite.domain.ports.github.GitHubPort;
import org.springframework.stereotype.Component;

@Component
public class GitHubAdapter implements GitHubPort {
  @Override
  public PRStatus getPullRequestStatus(String owner, String repo, int prNumber) {
    // TODO: implement real GitHub client. For now, return UNKNOWN.
    return PRStatus.UNKNOWN;
  }
}
