package com.abhinav.codingchallenge.egens.codingchallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class UserService {

    private DB userdb;
    private DBCollection userCollection;

    public UserService(DB userdb) {
        this.userdb = userdb;
        userCollection = userdb.getCollection("user_database");
    }

    public List<User> findAll() {

        List<User> users = new ArrayList<>();
        DBCursor userDbCursor = userCollection.find();
        while (userDbCursor.hasNext()) {
            users.add(new User((BasicDBObject) userDbCursor.next()));
        }
        return users;
    }

    public void createNewUser(String jsonReqString) {
        DBObject userBasicObj = (DBObject) JSON.parse(jsonReqString);
        userCollection.insert(userBasicObj);
    }

    public User find(String id) {
        BasicDBObject userObj = (BasicDBObject) userCollection.findOne(new BasicDBObject("id", id));
        if (userObj == null)
            return null;

        return new User(userObj);
    }

    public User update(String jsonString) {
        User user = new Gson().fromJson(jsonString, User.class);
        User retrievedUser = find(user.getId());
        boolean isUpdateRequired = updateUserFields(user, retrievedUser);

        if (isUpdateRequired) {
            BasicDBObject searchQuery = new BasicDBObject().append("id", user.getId());
            userCollection.update(searchQuery, (DBObject) JSON.parse(jsonString));
        }
        return find(user.getId());
    }

    private boolean updateUserFields(User user, User retrievedUser) {

        boolean isUpdateNeccassary = false;
        if (!Objects.equals(user.getFirstName(), retrievedUser.getFirstName())) {
            retrievedUser.setFirstName(user.getFirstName());
            isUpdateNeccassary = true;
        }
        if (!Objects.equals(user.getLastName(), retrievedUser.getLastName())) {
            retrievedUser.setLastName(user.getLastName());
            isUpdateNeccassary = true;
        }
        if (!Objects.equals(user.getEmail(), retrievedUser.getEmail())) {
            retrievedUser.setEmail(user.getEmail());
            isUpdateNeccassary = true;
        }
        if (!Objects.equals(user.getDateCreated(), retrievedUser.getDateCreated())) {
            retrievedUser.setDateCreated(user.getDateCreated());
            isUpdateNeccassary = true;
        }
        if (!Objects.equals(user.getProfilePic(), retrievedUser.getProfilePic())) {
            retrievedUser.setProfilePic(user.getProfilePic());
            isUpdateNeccassary = true;
        }
        if (!Objects.equals(user.getAddress(), retrievedUser.getAddress())) {
            retrievedUser.setAddress(user.getAddress());
            isUpdateNeccassary = true;
        }

        if (!Objects.equals(user.getCompany(), retrievedUser.getCompany())) {
            retrievedUser.setCompany(user.getCompany());
            isUpdateNeccassary = true;
        }

        return isUpdateNeccassary;
    }

}
