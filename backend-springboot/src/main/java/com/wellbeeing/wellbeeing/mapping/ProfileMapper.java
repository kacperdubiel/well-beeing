package com.wellbeeing.wellbeeing.mapping;

import com.wellbeeing.wellbeeing.api.model.ProfileModel;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProfileMapper {
    Profile profileModelToProfile(ProfileModel profileModel);
    ProfileModel profileToProfileModel(Profile profile);
    void update(@MappingTarget Profile profile, ProfileModel profileModel);
}
