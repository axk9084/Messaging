package axk9084.Messaging.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by antkwan on 9/16/2016.
 */
public class Message {
  private long id;

  private String author;
  private String message;

  // Empty constructor for Jackson
  public Message() {}

  public Message( long id, String author, String message ) {
    this.id = id;
    this.author = author;
    this.message = message;
  }

  @JsonProperty
  public long getId() {
    return id;
  }

  @JsonProperty
  public String getAuthor() {
    return author;
  }

  @JsonProperty
  public String getMessage() {
    return message;
  }
}
