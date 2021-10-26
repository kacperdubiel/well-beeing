package com.wellbeeing.wellbeeing.domain.telemedic;

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
    private EConnectionType connectionType;

    @ManyToOne
    private Profile firstProfile;
    @ManyToOne
    private Profile secondProfile;
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

    public Profile getFirstProfile() {
        return firstProfile;
    }

    public void setFirstProfile(Profile profile1) {
        this.firstProfile = profile1;
    }

    public Profile getSecondProfile() {
        return secondProfile;
    }

    public void setSecondProfile(Profile profile2) {
        this.secondProfile = profile2;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
