package com.chunmaru.sushishop.data.models.admin

import android.os.Parcel
import android.os.Parcelable


data class Admin(
    val login: String,
    val username: String,
    val email: String,
)

fun AdminResponse.toAdmin() =
    Admin(
        login = this.login,
        username = this.username,
        email = this.email
    )


data class AdminResponse(
    val login: String,
    val username: String,
    val email: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(username)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AdminResponse> {
        override fun createFromParcel(parcel: Parcel): AdminResponse {
            return AdminResponse(parcel)
        }

        override fun newArray(size: Int): Array<AdminResponse?> {
            return arrayOfNulls(size)
        }
    }

}