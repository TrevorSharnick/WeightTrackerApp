package com.sharnick.weighttrackerapplicationfinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is the Database class for user Weight objects.
 * It contains a wide assortment of methods for handling data including adding, deleting, and editing weight objects.
 */
public class WeightBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "weights.db";


    private static WeightBaseHelper instance;

    public WeightBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static WeightBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new WeightBaseHelper(context);
        }

        return instance;
    }

    private static final class WeightTable {
        private static final String TABLE = "weights";
        private static final String COL_ID = "_id";
        private static final String COL_WEIGHT = "weight";
        private static final String COL_DATE = "date";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + WeightTable.TABLE + "( " +
                WeightTable.COL_ID + " integer primary key autoincrement, " +
                WeightTable.COL_WEIGHT + " double, " +
                WeightTable.COL_DATE + " long)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + WeightTable.TABLE);
        onCreate(db);
    }

    public List<WeightModel> getWeights() {
        List<WeightModel> weights = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + WeightTable.TABLE;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                double weight = cursor.getDouble(1);
                long date = cursor.getLong(2);

                // Instantiate a new Date object from the long value
                Date dateObj = new Date(date);

                // Use the WeightModel(long, Date, double) constructor
                WeightModel weightModel = new WeightModel(id, dateObj, weight);

                weights.add(weightModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return weights;
    }

    public WeightModel getWeight(long weightId) {
        WeightModel weightModel = null;

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + WeightTable.TABLE + " WHERE " + WeightTable.COL_ID + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{Long.toString(weightId)});

        if (cursor.moveToFirst()) {
            double weight = cursor.getDouble(1);
            long date = cursor.getLong(2);

            weightModel = new WeightModel(weightId, new Date(date), weight);
        }

        cursor.close();
        db.close();

        return weightModel;
    }

    public long addWeight(WeightModel weightModel) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(WeightTable.COL_WEIGHT, weightModel.getWeight());
        values.put(WeightTable.COL_DATE, weightModel.getDate().getTime());

        long newId = db.insert(WeightTable.TABLE, null, values);
        db.close();
        return newId;
    }

    public boolean editWeight(long id, WeightModel weightModel) {
        boolean isEdited = false;
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WeightTable.COL_ID, id);
        values.put(WeightTable.COL_WEIGHT, weightModel.getWeight());
        values.put(WeightTable.COL_DATE, weightModel.getDate().getTime());

        int result = db.update(WeightTable.TABLE, values, WeightTable.COL_ID + " = " + id, null);

        db.close();
        return result == 1;
    }

    public boolean deleteWeight(long id) {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(WeightTable.TABLE, WeightTable.COL_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result == 1;
    }
}
