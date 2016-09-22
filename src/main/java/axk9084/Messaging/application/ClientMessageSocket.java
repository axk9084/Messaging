package axk9084.Messaging.application;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Created by antkwan on 9/20/2016.
 */
@WebSocket
public class ClientMessageSocket {

  private static final Logger log = LoggerFactory.getLogger( ClientMessageSocket.class );

  private Session session;
  private Callable<Void> doOnClose;

  public ClientMessageSocket( Callable<Void> doOnClose ) {
    this.doOnClose = doOnClose;
  }

  @OnWebSocketClose
  public void onClose( int statusCode, String reason ) {
    log.debug( "Closing session with server" );
    this.session = null;

    log.debug( "Running callable doOnClose" );
    try {
      doOnClose.call();
    } catch ( Exception e ) {
      log.error( "{} failed to run callable on web socket close: {}", this.getClass().getName(), e );
    }
  }

  @OnWebSocketConnect
  public void onConnect( Session session ) {
    log.debug( "Opening session with server" );
    this.session = session;
  }

  @OnWebSocketMessage
  public void onMessage( String message ) {
    log.debug( "Ignoring message from server: {}", message );
  }

  void sendMessage( String message ) {
    log.debug( "Sending message to server: {}", message );
    //todo error check future?
    if ( session != null ) {
      session.getRemote().sendStringByFuture( message );
    }
    else {
      log.debug( "There is no open session to send a message" );
    }
  }
}
