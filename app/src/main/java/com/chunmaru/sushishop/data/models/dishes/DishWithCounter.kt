package com.chunmaru.sushishop.data.models.dishes

import android.os.Parcel
import android.os.Parcelable


data class DishWithCounter(
    val dish: Dish,
    val counter: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Dish::class.java.classLoader)!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(dish, flags)
        parcel.writeInt(counter)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DishWithCounter> {
        override fun createFromParcel(parcel: Parcel): DishWithCounter {
            return DishWithCounter(parcel)
        }

        override fun newArray(size: Int): Array<DishWithCounter?> {
            return arrayOfNulls(size)
        }
    }
}