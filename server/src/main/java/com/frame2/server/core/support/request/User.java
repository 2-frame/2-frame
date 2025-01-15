package com.frame2.server.core.support.request;

import static com.frame2.server.core.support.request.User.Role.GUEST;
import static com.frame2.server.core.support.request.User.Role.MEMBER;

public record User(Long id, Role role) {

    public static User guest() {
        return new User(null, GUEST);
    }

    public static User member(Long id) {
        return new User(id, MEMBER);
    }

    public boolean isMember() {
        return this.role == MEMBER;
    }

    enum Role {
        GUEST,
        MEMBER
    }
}