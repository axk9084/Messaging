package axk9084.Messaging.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by antkwan on 9/16/2016.
 */
public class MessagingConfiguration extends Configuration {

  @NotEmpty
  private String serverWSEndpoint;

  @NotEmpty
  private String serverWSEndpointUriString;

  @JsonProperty
  public String getServerWSEndpoint() {
    return serverWSEndpoint;
  }

  @JsonProperty
  public void setServerWSEndpoint( String serverWSEndpoint ) {
    this.serverWSEndpoint = serverWSEndpoint;
  }

  @JsonProperty
  public String getServerWSEndpointUriString() {
    return serverWSEndpointUriString;
  }

  @JsonProperty
  public void setServerWSEndpointUriString( String serverWSEndpointUriString ) {
    this.serverWSEndpointUriString = serverWSEndpointUriString;
  }
}
