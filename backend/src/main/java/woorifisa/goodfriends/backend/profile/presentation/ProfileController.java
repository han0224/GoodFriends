package woorifisa.goodfriends.backend.profile.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.profile.application.ProfileService;
import woorifisa.goodfriends.backend.profile.dto.request.ProfileUpdateRequest;
import woorifisa.goodfriends.backend.user.application.UserService;

import javax.validation.Valid;
import java.io.IOException;

@RequestMapping("/api/profiles")
@RestController
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @PatchMapping("/me/info") // 닉네임, 핸드폰, 주소 수정
    public ResponseEntity<Void> update(@AuthenticationPrincipal LoginUser loginUser,
                                       @Valid @RequestBody final ProfileUpdateRequest profileUpdateRequest) {
        profileService.update(loginUser.getId(), profileUpdateRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/me/profile-image") // 프로필 이미지 수정
    public ResponseEntity<Void> updateImage(@AuthenticationPrincipal LoginUser loginUser,
                                            @RequestPart MultipartFile multipartFile) throws IOException {
        userService.saveProfileImage(loginUser.getId(), multipartFile);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}