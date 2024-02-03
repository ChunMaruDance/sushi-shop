package com.chunmaru.sushishop.data.models

import android.os.Parcel
import android.os.Parcelable
import com.chunmaru.sushishop.data.models.dishes.Ingredient

data class IngredientResponse(
    val id: Int,
    val name: String,
    val description: String,
    val image: ByteArray
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createByteArray()!!
    ) {
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IngredientResponse

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        return image.contentEquals(other.image)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeByteArray(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IngredientResponse> {
        override fun createFromParcel(parcel: Parcel): IngredientResponse {
            return IngredientResponse(parcel)
        }

        override fun newArray(size: Int): Array<IngredientResponse?> {
            return arrayOfNulls(size)
        }
    }
}

fun List<IngredientResponse>.toIngredients(): List<Ingredient> {
    return map {
        Ingredient(
            name = it.name,
            descriptions = it.description,
            img = it.image,
            id = it.id
        )
    }
}
