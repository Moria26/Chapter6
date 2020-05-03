package com.byted.camp.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.byted.camp.todolist.db.TodoContract.SQL_CREATE_ENTRIES;
import static com.byted.camp.todolist.db.TodoContract.TodoEntry.TABLE_NAME;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public class TodoDbHelper extends SQLiteOpenHelper {

    // TODO 定义数据库名、版本；创建数据库
    private static int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Todo.db";

    //单例模式
    private static volatile TodoDbHelper instance = null;

    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static TodoDbHelper getDBHelper(Context context) {
        if (instance == null) {
            synchronized (TodoDbHelper.class) {
                if (instance == null)
                    instance = new TodoDbHelper(context);
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN priority INTEGER default 0");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN priority INTEGER default 0");
        }
    }

}
