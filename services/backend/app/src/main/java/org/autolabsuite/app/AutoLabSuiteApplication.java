package org.autolabsuite.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.autolabsuite")
public class AutoLabSuiteApplication {
  public static void main(String[] args) {
    SpringApplication.run(AutoLabSuiteApplication.class, args);
  }
}
