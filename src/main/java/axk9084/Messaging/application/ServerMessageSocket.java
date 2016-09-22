package axk9084.Messaging.application;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by antkwan on 9/19/2016.
 */
@WebSocket
public class ServerMessageSocket implements WebSocketListener {

  private static Set<Session> sessions = Collections.synchronizedSet( new HashSet<Session>() );

  public void onWebSocketBinary( byte[] payload, int offset, int len ) {
    /* Do Nothing */
  }

  public void onWebSocketText( String message ) {
    // Echo to all sessions
    for ( Session s : sessions ) {
      if ( s.isOpen() ) {
        s.getRemote().sendStringByFuture( message );
      }
    }
  }

  public void onWebSocketClose( int statusCode, String reason ) {
    Set<Session> toRemove = new HashSet<Session>();
    for ( Session s : sessions ) {
      if ( !s.isOpen() ) {
        toRemove.add( s );
      }
    }
    for ( Session s : toRemove ) {
      sessions.remove( s );
    }
  }

  public void onWebSocketConnect( Session session ) {
    sessions.add( session );
  }

  public void onWebSocketError( Throwable cause ) {

  }
}
