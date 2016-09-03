package com.example.sergio.pruebaandroid_sergiogomez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sergio on 3/09/2016.
 */
public class UserDBHandler {
    //Base de datos para el manejo de los usuarios
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DATE = "date";
    public static final String KEY_PASSWORD = "password";
    private static final String TAG = "DBuser";

    public static final String DATABASE_NAME = "DB1";
    public static final String DATABASE_TABLE = "users";
    public static final int DATABASE_VERSION = 1;
    public static final String tag = "DBuser class";
    public static final String DATABASE_CREATE =
            "create table users (_id integer primary key autoincrement, "
                    + "name text not null, email text not null, date text not null, password text not null);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public UserDBHandler(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        //creacion de la base de datos,
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(DATABASE_CREATE);
                Log.d(tag,"Database Created Successfully");
            }catch(SQLException e){
                e.printStackTrace();
                Log.d(tag, "Error(hani): caught exception here... 101");
            }
        }

        //actualizacion de la base de datos si es necesario
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG,"Upgrading database from version "+ oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXITS friends");
            onCreate(db);
        }
    }// end of DataBaseHelper class

    //obtiene una base de datos que se pueda editar
    public UserDBHandler open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //cierra la base de datos
    public void close(){
        DBHelper.close();
    }

    //funcion encargada de ingresar un nuevo campo a la base de datos
    public long insertUser(String name, String email, String date,String pass){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_PASSWORD,pass);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }


    public boolean deleteContact (long rowid){
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + rowid,null)>0;
    }

    public Cursor getAllContacts(){
        return db.query(DATABASE_TABLE, new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_DATE,KEY_PASSWORD},null,null,null,null,null);
    }

    public Cursor getContact(long rowid) throws SQLException{
        Cursor mCursor =
                db.query(true,DATABASE_TABLE,new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_DATE,KEY_PASSWORD},KEY_ID + "=" + rowid, null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getContactbyname(String name)throws SQLException{
        Cursor mCursor = db.query(DATABASE_TABLE,new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_DATE,KEY_PASSWORD},KEY_NAME+"=?", new String[]{name},null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    /*public Cursor checkname(String name)throws SQLException{
        Cursor mCursor = db.query(DATABASE_TABLE,new String[]);
    }*/

    public boolean updateContact (long rowid, String name, String email, String date, String pass){
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_EMAIL, email);
        args.put(KEY_DATE, date);
        args.put(KEY_PASSWORD,pass);
        return db.update(DATABASE_TABLE, args, KEY_ID + "=" + rowid,null)>0;
    }
}
