package com.sbm.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class UserTest {

    @Test
    public void users_with_different_userId_should_not_be_equal() {
        User user1 = new User("user1");
        User user2 = new User("user2");
        assertFalse(user1.equals(user2));
    }

}
