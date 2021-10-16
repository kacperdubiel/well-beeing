package com.wellbeeing.wellbeeing.service.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.telemedic.ProfileConnectionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("profileConnectionService")
public class ProfileConnectionService implements ProfileConnectionServiceApi {
    private ProfileConnectionDAO profileConnectionDAO;

    @Autowired
    public ProfileConnectionService(@Qualifier("profileConnectionDAO") ProfileConnectionDAO profileConnectionDAO){
        this.profileConnectionDAO = profileConnectionDAO;
    }

    @Override
    public ProfileConnection getProfileConnectionById(UUID profileConnectionId) {
        return profileConnectionDAO.findById(profileConnectionId).orElse(null);
    }

    @Override
    public List<ProfileConnection> getProfileConnectionsByProfile(Profile profile) {
        return profileConnectionDAO.findByProfile(profile);
    }

    @Override
    public List<ProfileConnection> getProfileConnectionsByProfileAndType(Profile profile, EConnectionType connectionType) {
        return profileConnectionDAO.findByProfileAndConnectionType(profile, connectionType);
    }

    @Override
    public ProfileConnection addProfileConnection(ProfileConnection profileConnection) {
        return profileConnectionDAO.save(profileConnection);
    }

    @Override
    public ProfileConnection updateProfileConnection(ProfileConnection updatedProfileConnection) {
        ProfileConnection resultProfileConnection = profileConnectionDAO
                .findById(updatedProfileConnection.getId())
                .orElse(null);

        if(resultProfileConnection != null){
            resultProfileConnection.setConnectionType(updatedProfileConnection.getConnectionType());
            resultProfileConnection.setAccepted(updatedProfileConnection.isAccepted());
            resultProfileConnection.setRequestDate(updatedProfileConnection.getRequestDate());
            resultProfileConnection.setAcceptDate(updatedProfileConnection.getAcceptDate());
            resultProfileConnection.setProfile(updatedProfileConnection.getProfile());
            resultProfileConnection.setConnectedWith(updatedProfileConnection.getConnectedWith());
            return profileConnectionDAO.save(resultProfileConnection);
        }
        return null;
    }

    @Override
    public boolean deleteProfileConnectionById(UUID profileConnectionId) {
        if(profileConnectionDAO.findById(profileConnectionId).isPresent()){
            profileConnectionDAO.deleteById(profileConnectionId);
            return true;
        }
        return false;
    }






}
