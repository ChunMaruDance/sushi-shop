package com.chunmaru.sushishop.data.models.dishes

import android.os.Parcel
import android.os.Parcelable


class DishesReceiveRemote(
    val id: Int = 0,
    val name: String,
    val descriptions: String,
    val price: Float,
    val discount: Float,
    val weight: Float,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(descriptions)
        parcel.writeFloat(price)
        parcel.writeFloat(discount)
        parcel.writeFloat(weight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DishesReceiveRemote> {
        override fun createFromParcel(parcel: Parcel): DishesReceiveRemote {
            return DishesReceiveRemote(parcel)
        }

        override fun newArray(size: Int): Array<DishesReceiveRemote?> {
            return arrayOfNulls(size)
        }
    }


}