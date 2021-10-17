package com.wellbeeing.wellbeeing.domain.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private EConnectionType connectionType;
    @Column
    private EMessageType messageType;
    @Column
    private String data;
    @Column
    private Date createDate;
    @Column
    private boolean isRead;

    @ManyToOne
    private Profile sender;
    @ManyToOne
    private Profile receiver;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(EConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public EMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(EMessageType messageType) {
        this.messageType = messageType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Profile getSender() {
        return sender;
    }

    public void setSender(Profile sender) {
        this.sender = sender;
    }

    public Profile getReceiver() {
        return receiver;
    }

    public void setReceiver(Profile receiver) {
        this.receiver = receiver;
    }
}
