package com.marcosvcg.projeto.util;

public interface ChangeUserCredentials {

    void changeUsername(Long userId, String newUsername);
    void changePassword(Long userId, String newPassword);
    void changeEmail(Long userId, String newEmail);
}
