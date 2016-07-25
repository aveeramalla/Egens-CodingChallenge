package com.abhinav.codingchallenge.egens.codingchallenge;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class ApplicationStart {

    private static final String HOST_NAME = "localhost";
    private static final int PORT = 27017;

    public static void main(String[] args) throws Exception {

        MongoClient mClient = new MongoClient(new ServerAddress(HOST_NAME, PORT));
        mClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mClient.getDB("user_database");
        new UserResource(new UserService(db));
    }

}
