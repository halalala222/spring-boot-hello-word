package com.github.halalala222.sprintboothelloword.service;

import com.github.halalala222.sprintboothelloword.dto.UserProfileDTO;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
public interface UserProfileService {
    UserProfileDTO getUserProfile(Long userId);

    void updateUserProfile(UserProfileDTO userProfileDTO);
}
