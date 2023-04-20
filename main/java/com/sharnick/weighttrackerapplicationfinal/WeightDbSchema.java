package com.sharnick.weighttrackerapplicationfinal;

/**
 * The WeightDbSchema class provides constants that define the database schema for the WeightModel class.
 *
 * The WeightTable class contains the name of the table (NAME) and the names of the columns in the table (Cols).
 *
 * The Cols class defines the column names for the ID, DATE, and WEIGHT columns.
 */
public class WeightDbSchema {
    public static final class WeightTable {
        public static final String NAME = "weights";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String DATE = "date";
            public static final String WEIGHT = "weight";
        }
    }
}
