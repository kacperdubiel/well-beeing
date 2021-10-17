package com.wellbeeing.wellbeeing.domain.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "profile_connections")
public class ProfileConnection {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private EConnectionType connectionType;
    @Column
    private boolean isAccepted;
    @Column
    private Date requestDate;
    @Column
    private Date acceptDate;

    @ManyToOne
    private Profile profile;
    @ManyToOne
    private Profile connectedWith;

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

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getConnectedWith() {
        return connectedWith;
    }

    public void setConnectedWith(Profile connectedWith) {
        this.connectedWith = connectedWith;
    }
}
