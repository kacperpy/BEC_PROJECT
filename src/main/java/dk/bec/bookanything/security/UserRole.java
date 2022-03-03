package dk.bec.bookanything.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    REGULAR_USER("REGULAR_USER"),
    PATIENT("PATIENT");

    private final String name;
}
