package axk9084.Messaging.application.resource;

import axk9084.Messaging.api.Message;
import axk9084.Messaging.application.MessageClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by antkwan on 9/16/2016.
 */
@Path( "messaging" )
@Consumes( MediaType.TEXT_PLAIN )
@Produces( MediaType.APPLICATION_JSON )
public class MessagingResource {

  private final MessageClient messageClient;
  private final AtomicLong idCounter;

  public MessagingResource( MessageClient messageClient ) {
    this.idCounter = new AtomicLong();
    this.messageClient = messageClient;
  }

  @POST
  public Message handleMessage( @QueryParam( "author" ) @DefaultValue( "anon" ) String author,
                                String messageContent ) {

    messageClient.sendMessage( String.format( "%s: %s", author, messageContent ) );

    return new Message( idCounter.incrementAndGet(), author, messageContent );
  }

}

