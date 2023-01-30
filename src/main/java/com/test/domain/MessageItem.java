/**
 * 
 */
package com.test.domain;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "Messages")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageItem implements Serializable {
    @Column(name = "correlationId", nullable = false)
	@Id
    private String correlationId;
    @Column(name = "topicName", nullable = false)
    private long topicName;
    @Column(name = "message", nullable = false)
    private String message;

}
