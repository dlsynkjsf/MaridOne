package org.example.maridone.core.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

//    @PostMapping("/{username}")
//    public ResponseEntity<UserAccountDto> createUserAccount(@PathVariable String username, @RequestBody UserAccount userAccount) {
//
//    }

}
