package dev.lamp.dao;

import dev.lamp.models.Manager;

public interface ManagerDAO {
    Manager getManagerById(int id);
    Manager login(String username, String password);
}
