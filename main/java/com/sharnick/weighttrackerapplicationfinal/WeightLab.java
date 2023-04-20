package com.sharnick.weighttrackerapplicationfinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is a class that manages the SQLite database for the weight tracking application.
 * It provides methods for getting, adding, updating, and deleting weight records.
 * It also stores and retrieves the user's goal weight from shared preferences.
 *
 * The class uses a cursor to query the database and wrap the results into WeightModel objects.
 * It also contains methods for creating and updating the database schema, and for managing transactions.
 *
 * The class is implemented as a singleton, ensuring that only one instance is created throughout the application's lifecycle.
 */
public class WeightLab {

    private static WeightLab sWeightLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private double mGoalWeight; // variable for keeping track of the user's goal weight

    public static WeightLab get(Context context) {
        if (sWeightLab == null) {
            sWeightLab = new WeightLab(context);
        }
        return sWeightLab;
    }

    private WeightLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new WeightBaseHelper(mContext).getWritableDatabase();
        mGoalWeight = 0; // initialized mGoalWeight to zero

        SharedPreferences preferences = getSharedPreferences();
        mGoalWeight = preferences.getFloat("goal_weight", 0);
    }
    // setters and getters for goal weight
    public double getGoalWeight() {
        return mGoalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        mGoalWeight = goalWeight;
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("goal_weight", (float) mGoalWeight);
        editor.apply();
    }

    public WeightModel getWeight(long weightId) {
        WeightCursorWrapper cursor = queryWeights(
                WeightDbSchema.WeightTable.Cols.ID + " = ?",
                new String[] { Long.toString(weightId) }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            long id = cursor.getLong(cursor.getColumnIndex(WeightDbSchema.WeightTable.Cols.ID));
            long date = cursor.getLong(cursor.getColumnIndex(WeightDbSchema.WeightTable.Cols.DATE));
            double weight = cursor.getDouble(cursor.getColumnIndex(WeightDbSchema.WeightTable.Cols.WEIGHT));
            return new WeightModel(id, new Date(date), weight);
        } finally {
            cursor.close();
        }
    }

    public List<WeightModel> getWeights() {
        List<WeightModel> weights = new ArrayList<>();
        WeightCursorWrapper cursor = queryWeights(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                long id = cursor.getLong(cursor.getColumnIndex(WeightDbSchema.WeightTable.Cols.ID));
                long date = cursor.getLong(cursor.getColumnIndex(WeightDbSchema.WeightTable.Cols.DATE));
                double weight = cursor.getDouble(cursor.getColumnIndex(WeightDbSchema.WeightTable.Cols.WEIGHT));

                WeightModel weightModel = new WeightModel(id, new Date(date), weight);
                weights.add(weightModel);

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return weights;
    }

    public long addWeight(WeightModel weightModel) {
        ContentValues values = new ContentValues();
        values.put(WeightDbSchema.WeightTable.Cols.WEIGHT, weightModel.getWeight());
        values.put(WeightDbSchema.WeightTable.Cols.DATE, weightModel.getDate().getTime());

        long newId = mDatabase.insert(WeightDbSchema.WeightTable.NAME, null, values);

        return newId;
    }

    public void deleteWeight(WeightModel weight) {
        long id = weight.getId();
        mDatabase.delete(WeightDbSchema.WeightTable.NAME, WeightDbSchema.WeightTable.Cols.ID + " = ?", new String[] { Long.toString(id) });
    }

    public void updateWeight(WeightModel weight) {
        long id = weight.getId();
        ContentValues values = getContentValues(weight);
        mDatabase.update(WeightDbSchema.WeightTable.NAME, values, WeightDbSchema.WeightTable.Cols.ID + " = ?", new String[] { Long.toString(id) });
    }

    private static ContentValues getContentValues(WeightModel weight) {
        ContentValues values = new ContentValues();
        values.put(WeightDbSchema.WeightTable.Cols.ID, weight.getId());
        values.put(WeightDbSchema.WeightTable.Cols.DATE, weight.getDate().getTime());
        values.put(WeightDbSchema.WeightTable.Cols.WEIGHT, weight.getWeight());
        return values;
    }


    private WeightCursorWrapper queryWeights(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                WeightDbSchema.WeightTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new WeightCursorWrapper(cursor);
    }

    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
    }
}

