# Messaging
Simple message web service that allows text messages to be POSTed
and forwards the message to any clients connected via web socket.

REST endpoint:
 * POST http://localhost:8080/messaging
   * Raw post body is the content of the message
   * Optional Query Parameter: author - the author of the message
     * Example: http://localhost:8080/messaging?author=axk9084

WebSocket endpoint:
  * (Default) ws://localhost:8080/websocket
  * Configurable in Messaging.yml (serverWSEndpoint and serverWSEndpointUriString)
  * Default timeout is 10 minutes. Also configurable in Messaging.yml

Basic setup and startup steps:

mvn package

java -jar target\Messaging-1.0-SNAPSHOT.jar server Messaging.yml
