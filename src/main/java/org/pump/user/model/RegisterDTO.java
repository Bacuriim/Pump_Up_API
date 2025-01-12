package org.pump.user.model;

import org.pump.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
