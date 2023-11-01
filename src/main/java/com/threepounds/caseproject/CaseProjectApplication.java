package com.threepounds.caseproject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class CaseProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(CaseProjectApplication.class, args);

  }

}
