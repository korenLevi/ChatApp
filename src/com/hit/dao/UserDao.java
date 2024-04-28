package com.hit.dao;

import com.hit.dm.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class UserDao implements Dao<User> {
    private final String fileName;

    public UserDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public User get(Long id) {
        ArrayList<User> users = getAll();
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 7) {
                    continue;
                }
                User user = new User();
                user.setId(Long.parseLong(parts[0]));
                user.username = parts[1];
                user.fullName = parts[2];
                user.password = parts[3];
                user.email = parts[4];
                user.gender = parts[5];
                user.phoneNo = parts[6];
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String line = user.getId() + "," + user.username + "," + user.fullName + ","
                    + user.password + "," + user.email + "," + user.gender + ","
                    + user.phoneNo + "\n";
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user, String[] params) {
        user.username = params[0];
        user.fullName = params[1];
        user.password = params[2];
        user.email = params[3];
        user.gender = params[4];
        user.phoneNo = params[5];
        saveAll(getAll());
    }

    @Override
    public void delete(User user) {
        ArrayList<User> users = getAll();
        users.removeIf(u -> u.getId() == user.getId());
        saveAll(users);
    }

    private void saveAll(ArrayList<User> users) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (User user : users) {
                String line = user.getId() + "," + user.username + "," + user.fullName + ","
                        + user.password + "," + user.email + "," + user.gender + ","
                        + user.phoneNo + "\n";
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
