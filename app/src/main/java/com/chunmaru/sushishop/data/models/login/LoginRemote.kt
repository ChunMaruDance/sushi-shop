package com.chunmaru.sushishop.data.models.login

import android.os.Parcel
import android.os.Parcelable


data class LoginReceive(
    val login: String,
    val password: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginReceive> {
        override fun createFromParcel(parcel: Parcel): LoginReceive {
            return LoginReceive(parcel)
        }

        override fun newArray(size: Int): Array<LoginReceive?> {
            return arrayOfNulls(size)
        }
    }

}


data class LoginResponse(
    val token: String
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginResponse> {
        override fun createFromParcel(parcel: Parcel): LoginResponse {
            return LoginResponse(parcel)
        }

        override fun newArray(size: Int): Array<LoginResponse?> {
            return arrayOfNulls(size)
        }
    }
}