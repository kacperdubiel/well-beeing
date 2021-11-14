package com.wellbeeing.wellbeeing.domain.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private EMessageType messageType;
    @Column(nullable = false, columnDefinition="TEXT")
    private String data;
    @Column
    private Date createDate;

    @ManyToOne
    private Profile sender;
    @ManyToOne
    private Conversation conversation;

}
