package axk9084.Messaging.application;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

/**
 * Created by antkwan on 9/19/2016.
 */
@WebServlet( name = "WebSocket Servlet" )
public class ServerServlet extends WebSocketServlet {

  private static final long MINUTE_IN_MILLI = 60 * 1000;

  private final long timeoutInMin;

  ServerServlet( long timeoutInMin ) {
    this.timeoutInMin = timeoutInMin;
  }

  public void configure( WebSocketServletFactory factory ) {
    factory.getPolicy().setIdleTimeout( timeoutInMin * MINUTE_IN_MILLI );
    factory.register( ServerMessageSocket.class );
  }
}
