package axk9084.Messaging.application;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Callable;

/**
 * Created by antkwan on 9/19/2016.
 */
public class MessageClient {

  private static final Logger log = LoggerFactory.getLogger( MessageClient.class );

  private final URI serverEndpointURI;
  private final ClientMessageSocket webSocket;
  private final WebSocketClient webSocketClient;

  MessageClient( final URI serverEndpointURI ) {
    ConnectCallable connectCallable = new ConnectCallable();
    this.serverEndpointURI = serverEndpointURI;
    this.webSocket = new ClientMessageSocket( connectCallable );
    webSocketClient = new WebSocketClient();
    log.debug( "Starting WebSocketClient" );
    start();
    connectCallable.call();
  }

  private void start() {
    try {
      if ( webSocketClient.isStopped() ) {
        webSocketClient.start();
      }
    }
    catch (Throwable t) {
      log.error( "WebSocketClient failed to start.", t );
    }
  }

  private void stop() {
    try {
      log.debug( "Stopping WebSocketClient" );

      if ( !webSocketClient.isStopped() || !webSocketClient.isStopping() ) {
        webSocketClient.stop();
      }

    }
    catch ( Exception e ) {
      log.error( "Failed to stop WebSocketClient: {}", e.getMessage(), e );
    }
  }

  public void sendMessage( String message ) {
    log.debug( "Sending message to server: {}", message );
    webSocket.sendMessage( message );
  }

  private class ConnectCallable implements Callable<Void> {

    public Void call() {
      try {
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        log.debug( "Attempting to connect to serverEndpoint" );
        webSocketClient.connect( webSocket, serverEndpointURI, request );
      }
      catch ( IOException e ) {
        e.printStackTrace();
      }
      return null;
    }
  }

}
