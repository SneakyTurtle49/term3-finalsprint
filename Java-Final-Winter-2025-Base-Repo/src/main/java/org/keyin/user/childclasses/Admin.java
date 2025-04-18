package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Admin extends User {

    public Admin(int id, String username, String password, String email, String phoneNumber, String address, String role) {
        super(id, username, password, email, phoneNumber, address, role);
    }

    public Admin(String username, String password, String email, String phoneNumber, String address) {
        super(username, password, email, phoneNumber, address, "Admin");
    }

}
