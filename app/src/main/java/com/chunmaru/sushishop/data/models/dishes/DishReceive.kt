package com.chunmaru.sushishop.data.models.dishes

import android.os.Parcel
import android.os.Parcelable

class DishReceive(
    val dish: DishesReceiveRemote,
    val byteArray: ByteArray
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(DishesReceiveRemote::class.java.classLoader)!!,
        parcel.createByteArray()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(dish, flags)
        parcel.writeByteArray(byteArray)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DishReceive> {
        override fun createFromParcel(parcel: Parcel): DishReceive {
            return DishReceive(parcel)
        }

        override fun newArray(size: Int): Array<DishReceive?> {
            return arrayOfNulls(size)
        }
    }
}