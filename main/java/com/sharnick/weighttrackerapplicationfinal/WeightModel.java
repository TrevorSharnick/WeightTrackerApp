package com.sharnick.weighttrackerapplicationfinal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Author: Trevor Sharnick
 * CS-360 T4232 Mobile Architect & Programming 23EW4
 * Final Project: "WeightTracker"
 * 4/12/2023
 *
 *
 * The WeightModel class is a model class that represents a weight object in the app.
 * It has three private fields: id, date, and weight.
 * The id field is a long that represents the unique identifier of the weight.
 * The date field is a Date object that represents the date the weight was recorded.
 * The weight field is a double that represents the value of the weight.
 *
 * The WeightModel class implements the Parcelable interface,
 * which allows instances of the class to be passed between components of the app.
 * The class has a constructor that takes the id, date, and weight as arguments.
 * It also has a second constructor that takes only the weight and date as arguments.
 */

public class WeightModel implements Parcelable {
    private long id;
    private Date date;
    private double weight;

    public WeightModel(long id, Date date, double weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public WeightModel(double weight, Date date) {
        this.weight = weight;
        this.date = date;
    }

    protected WeightModel(Parcel in) {
        id = in.readLong();
        long dateMillis = in.readLong();
        date = new Date(dateMillis);
        weight = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(date.getTime());
        dest.writeDouble(weight);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeightModel> CREATOR = new Creator<WeightModel>() {
        @Override
        public WeightModel createFromParcel(Parcel in) {
            return new WeightModel(in);
        }

        @Override
        public WeightModel[] newArray(int size) {
            return new WeightModel[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

