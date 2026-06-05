package com.rtechnologies.samar.roomdb;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rtechnologies.samar.Samar;
import com.rtechnologies.samar.roomdb.dao.ChatDao;
import com.rtechnologies.samar.roomdb.dao.ChatGroupDao;
import com.rtechnologies.samar.roomdb.schema.ChatGroupSchema;
import com.rtechnologies.samar.roomdb.schema.ChatSchema;

@androidx.room.Database(entities = {ChatSchema.class, ChatGroupSchema.class},version = 1,exportSchema = false)
public abstract class Db extends RoomDatabase {
    private static final String DB_NAME="SAMAR_DB";
    public static Db instance;

    public static synchronized Db getInstance() {
        if(instance==null){
            instance= Room.databaseBuilder(Samar.getGlobalContext(),Db.class,DB_NAME).build();
        }
        return instance;
    }
    public abstract ChatDao chatDao();
    public abstract ChatGroupDao chatGroupDao();



}
