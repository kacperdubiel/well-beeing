package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.repository.telemedic.ProfileConnectionDAO;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service("profileConnectionService")
public class ProfileConnectionServiceImpl implements ProfileConnectionService {
    private ProfileConnectionDAO profileConnectionDAO;
    private ProfileService profileService;

    @Autowired
    public ProfileConnectionServiceImpl(@Qualifier("profileConnectionDAO") ProfileConnectionDAO profileConnectionDAO,
                                        @Qualifier("profileService") ProfileService profileService)
    {
        this.profileConnectionDAO = profileConnectionDAO;
        this.profileService = profileService;
    }

    @Override
    public ProfileConnection getProfileConnectionById(UUID profileConnectionId) throws NotFoundException {
        ProfileConnection profileConnection = profileConnectionDAO.findById(profileConnectionId).orElse(null);
        if(profileConnection == null) {
            throw new NotFoundException("Profile connection with id: " + profileConnectionId + " not found");
        }

        return profileConnection;
    }

    @Override
    public Page<ProfileConnection> getProfileConnectionsByProfileAndTypeAndIsAccepted(
            Profile profile, EConnectionType connectionType, boolean isAccepted, int page, int size)
    {
        if(connectionType == EConnectionType.WITH_USER){
            return profileConnectionDAO.findByProfileOrConnectedWithAndConnectionTypeAndIsAccepted(
                    profile, connectionType, isAccepted, PageRequest.of(page, size));
        }

        return profileConnectionDAO.findByProfileAndConnectionTypeAndIsAccepted(profile, connectionType, isAccepted,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "requestDate")));
    }

    @Override
    public Page<ProfileConnection> getProfileConnectionsByConnectedWithAndTypeAndIsAccepted(Profile connectedWith,
            EConnectionType connectionType, boolean isAccepted, int page, int size)
    {
        if(connectionType == EConnectionType.WITH_USER){
            return profileConnectionDAO.findByProfileOrConnectedWithAndConnectionTypeAndIsAccepted(
                    connectedWith, connectionType, isAccepted, PageRequest.of(page, size));
        }

        return profileConnectionDAO.findByConnectedWithAndConnectionTypeAndIsAccepted(connectedWith, connectionType, isAccepted,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "requestDate")));
    }

    @Override
    public ProfileConnection getProfileConnectionByProfileAndConnectedWithAndType(Profile profile, Profile connectedWith, EConnectionType connectionType) {
        return profileConnectionDAO.findByProfileAndConnectedWithAndConnectionType(profile, connectedWith, connectionType);
    }

    @Override
    public ProfileConnection addProfileConnection(ProfileConnection profileConnection)
            throws ConflictException, NotFoundException
    {
        UUID pConnId = profileConnection.getId();
        if(pConnId != null){
            ProfileConnection pConnResult = profileConnectionDAO.findById(pConnId).orElse(null);
            if(pConnResult != null) {
                throw new ConflictException("Profile connection with id: " + pConnId + " already exists!");
            }
        }

        UUID profileId = profileConnection.getProfile().getId();
        UUID connectedWithId = profileConnection.getConnectedWith().getId();

        Profile profile = profileService.getProfileById(profileId);
        Profile connectedWithProfile = profileService.getProfileById(connectedWithId);
        EConnectionType connectionType = profileConnection.getConnectionType();

        ProfileConnection existingProfileConn = getProfileConnectionByProfileAndConnectedWithAndType(profile,
                connectedWithProfile, connectionType);
        if(existingProfileConn != null){
            throw new ConflictException("That profile connection already exists!");
        }

        profileConnection.setRequestDate(new Date());
        profileConnection.setAcceptDate(null);
        profileConnection.setAccepted(false);
        return profileConnectionDAO.save(profileConnection);
    }

    @Override
    public ProfileConnection markAsAccepted(ProfileConnection profileConnection) throws NotFoundException, ConflictException {
        UUID pConnId = profileConnection.getId();

        if(pConnId == null){
            throw new NotFoundException("Specify profile connection id!");
        }

        ProfileConnection pConnResult = profileConnectionDAO.findById(pConnId).orElse(null);
        if(pConnResult == null) {
            throw new NotFoundException("Profile connection with id: " + pConnId + " not found!");
        }

        if(profileConnection.isAccepted()){
            throw new ConflictException("That profile connection is already accepted!");
        }

        profileConnection.setAcceptDate(new Date());
        profileConnection.setAccepted(true);
        return profileConnectionDAO.save(profileConnection);
    }

    @Override
    public void deleteProfileConnectionById(UUID profileConnectionId) throws NotFoundException {
        ProfileConnection pConnResult = profileConnectionDAO.findById(profileConnectionId).orElse(null);
        if(pConnResult == null) {
            throw new NotFoundException("Profile connection with id: " + profileConnectionId + " not found!");
        }

        profileConnectionDAO.deleteById(profileConnectionId);
    }

}
