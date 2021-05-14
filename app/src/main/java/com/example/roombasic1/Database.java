package com.example.roombasic1;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class}, version = 2)
public abstract class Database extends RoomDatabase {
    public abstract UserDao userDao();
}