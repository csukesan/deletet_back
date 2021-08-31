package com.deletet.deletet3.Profile;

import com.deletet.deletet3.appuser.AppUser;
import com.deletet.deletet3.appuser.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/deletet")
@RestController
public class ProfileController {

    private AppUser appuser;
    private AppUserRepository appUserRepository;
    private final ProfileRepository profileRepository;

    public ProfileController(AppUserRepository appUserRepository, ProfileRepository profileRepository) {
        this.appUserRepository = appUserRepository;
        this.profileRepository = profileRepository;
    }


    @GetMapping("/profile/{id}")
    public ResponseEntity<List<ProfileDTO>> read(@PathVariable Long id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            appuser = appUser.get();
            List<Profile> profiles = profileRepository.findAll();
            List<ProfileDTO> profileDTOS = new ArrayList<>();
            for (Profile profile : profiles) {
                if (profile.getUserId() == appuser.getId()) {
                    profileDTOS.add(new ProfileDTO(profile.getId(), profile.getUserId(), profile.getImageUrl(), profile.getFullName(), profile.getAbout(),
                            profile.getTckn(), profile.getAddress(), profile.getWebsite(), profile.getPhoneNumber(),
                            profile.getEmail(), profile.getUniversity(), profile.getExperiences(), profile.getFollowingTitles(), profile.getProfilePoints(), profile.getForumPoints()));
                }
            }
            return new ResponseEntity<>(profileDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("profile/update/{id}")
    public ResponseEntity<Profile> update(@RequestBody Profile request, @PathVariable Long id) {
        Long tempId = null;
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            appuser = appUser.get();
            List<Profile> profiles = profileRepository.findAll();
            for (Profile profile : profiles) {
                if (profile.getUserId() == appuser.getId()) {
                    if(request.getImageUrl() != null)
                    {
                        profile.setImageUrl(request.getImageUrl());
                    }
                    if(request.getFullName() != null)
                    {
                        profile.setFullName(request.getFullName());
                    }
                    if(request.getAbout() != null)
                    {
                        profile.setAbout(request.getAbout());
                    }
                    if(request.getTckn()!=null)
                    {
                        profile.setTckn(request.getTckn());
                    }
                    if(request.getAddress()!=null)
                    {
                        profile.setAddress(request.getAddress());

                    }
                    if(request.getWebsite()!=null)
                    {
                        profile.setWebsite(request.getWebsite());

                    }
                    if(request.getPhoneNumber()!=null)
                    {
                        profile.setPhoneNumber(request.getPhoneNumber());
                    }
                    if(request.getEmail()!=null)
                    {
                        profile.setEmail(request.getEmail());
                    }
                    if(request.getUniversity()!=null)
                    {
                        profile.setUniversity(request.getUniversity());
                    }
                    if(request.getExperiences()!=null)
                    {
                        profile.setExperiences(request.getExperiences());
                    }
                    if(request.getFollowingTitles()!=null)
                    {
                        profile.setFollowingTitles(request.getFollowingTitles());
                    }
                    profile = profileRepository.save(profile);
                    tempId = profile.getId();
                }
            }
            Optional<Profile> temp = profileRepository.findById(tempId);
            Profile temp2 = temp.get();
            return new ResponseEntity<>(temp2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping ("profile/updateProfilePoint/{id}")
    public int updateProfilePoints(@PathVariable Long id) {
        Long tempId = null;
        int point = 0;
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            appuser = appUser.get();
            List<Profile> profiles = profileRepository.findAll();
            for (Profile profile : profiles) {
                if (profile.getUserId() == appuser.getId()) {
                    point = profile.calculateProfilePoints(profile);
                }
            }
            return point;
        } else {
            return 0;
        }
    }

}

