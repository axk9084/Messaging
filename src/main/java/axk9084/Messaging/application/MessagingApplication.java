package axk9084.Messaging.application;

import axk9084.Messaging.application.resource.MessagingResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by antkwan on 9/16/2016.
 */
public class MessagingApplication extends Application<MessagingConfiguration> {

  private static Logger log = LoggerFactory.getLogger( MessagingApplication.class );

  public static void main( String[] args ) throws Exception {
    new MessagingApplication().run( args );
  }

  @Override
  public void run( MessagingConfiguration messagingConfiguration, Environment environment ) throws Exception {
    try {
      log.debug( "Adding servlet for server web socket" );
      environment.servlets().addServlet( "Websocket", new ServerServlet() )
          .addMapping( messagingConfiguration.getServerWSEndpoint() );

      log.debug( "Constructing a message client" );
      MessageClient messageClient = new MessageClient( new URI( messagingConfiguration.getServerWSEndpointUriString() ) );

      log.debug( "Constructing and registering Messaging resource" );
      final MessagingResource messagingResource = new MessagingResource( messageClient );
      environment.jersey().register( messagingResource );

    } catch ( URISyntaxException e ) {
      log.error( "Error generating URI string", e );
    }
  }
}
