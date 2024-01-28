package com.chunmaru.sushishop.data.models

import android.os.Parcel
import android.os.Parcelable
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.data.models.dishes.DishResponse


data class Category(
    val id: String,
    val name: String,
    val descriptions: String,
)

data class CategoryResponse(
    val id: String,
    val name: String,
    val descriptions: String,
) : Parcelable {


    fun toCategory(): Category =
        Category(
            id = id,
            name = name,
            descriptions = descriptions
        )

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(descriptions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryResponse> {

        fun List<CategoryResponse>.toCategoriesList(): List<Category> {
            return map { it.toCategory() }
        }

        override fun createFromParcel(parcel: Parcel): CategoryResponse {
            return CategoryResponse(parcel)
        }

        override fun newArray(size: Int): Array<CategoryResponse?> {
            return arrayOfNulls(size)
        }
    }
}