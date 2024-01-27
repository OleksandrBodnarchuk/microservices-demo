package pl.alex.employeeservice.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UrlParameters {

  private String url;

  private String id;

  private String path;

}
