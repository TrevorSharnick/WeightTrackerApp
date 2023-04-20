package com.sharnick.weighttrackerapplicationfinal;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;

public class WeightCursorWrapper extends CursorWrapper {
    public WeightCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    // It provides a getWeight() method that retrieves weight data from a Cursor object and returns it as a WeightModel object.
    public WeightModel getWeight() {
        long id = getLong(getColumnIndex(WeightDbSchema.WeightTable.Cols.ID));
        long date = getLong(getColumnIndex(WeightDbSchema.WeightTable.Cols.DATE));
        double weightValue = getDouble(getColumnIndex(WeightDbSchema.WeightTable.Cols.WEIGHT));
        // The method uses the getColumnIndex() method to get the index of the columns in the Cursor,
        // and then retrieves the values using the appropriate get methods.
        // Finally, it constructs and returns a new WeightModel object with the retrieved values.
        WeightModel weight = new WeightModel(id, new Date(date), weightValue);

        return weight;
    }
}
