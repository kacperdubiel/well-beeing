package com.wellbeeing.wellbeeing.domain.telemedic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "conversations")
public class Conversation {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private EConnectionType connectionType;

    @Column
    private boolean isReadByFirstUser;
    @Column
    private boolean isReadBySecondUser;

    @ManyToOne
    private Profile firstProfile; // Basic_user if connectionType == WITH_{SPECIALIST}
    @ManyToOne
    private Profile secondProfile; // Specialist if connectionType == WITH_{SPECIALIST}
    @JsonIgnore
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> messages;

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

    public boolean isReadByFirstUser() {
        return isReadByFirstUser;
    }

    public void setReadByFirstUser(boolean readByFirstUser) {
        isReadByFirstUser = readByFirstUser;
    }

    public boolean isReadBySecondUser() {
        return isReadBySecondUser;
    }

    public void setReadBySecondUser(boolean readBySecondUser) {
        isReadBySecondUser = readBySecondUser;
    }

    public Profile getFirstProfile() {
        return firstProfile;
    }

    public void setFirstProfile(Profile firstProfile) {
        this.firstProfile = firstProfile;
    }

    public Profile getSecondProfile() {
        return secondProfile;
    }

    public void setSecondProfile(Profile secondProfile) {
        this.secondProfile = secondProfile;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
