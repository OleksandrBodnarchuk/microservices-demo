package pl.alex.employeeservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;
import pl.alex.employeeservice.exception.UnexpectedServiceException;

interface CallForDepartmentDetails {

  ObjectMapper getObjectMapper();

  default <T> T getValueFromJson(String jsonResponse, Class<T> clazz) {
    try {
      return getObjectMapper().readValue(jsonResponse, clazz);
    } catch (JsonProcessingException e) {
      throw new UnexpectedServiceException(e.getMessage());
    }
  }

  @SneakyThrows
  default URI prepareUrl(UrlParameters parameters) {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
        .uri(new URI(parameters.getUrl()));

    if (StringUtils.isNotEmpty(parameters.getPath())) {
      uriBuilder.path(parameters.getPath());
    }

    if (StringUtils.isNotEmpty(parameters.getId())) {
      uriBuilder.queryParam("id", parameters.getId());
    }

    return uriBuilder
        .build()
        .toUri();
  }
}
