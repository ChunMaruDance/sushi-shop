package com.chunmaru.sushishop.data.models.dishes

import android.os.Parcel
import android.os.Parcelable

class DishResponse(
    val id: Int,
    val name: String,
    val descriptions: String,
    val price: Float,
    val discount: Float,
    val weight: Float,
    val image: ByteArray,
    val category: String
) {
    fun toDish(): Dish =
        Dish(
            id = id,
            name = name,
            descriptions = descriptions,
            price = price,
            discount = discount,
            weight = weight,
            image = image,
            category = category
        )

    companion object {
        fun List<DishResponse>.toDishList(): List<Dish> {
            return map { it.toDish() }
        }
    }

}


data class Ingredient(
    val name: String,
    val descriptions: String,
    val id: Int,
    val img: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ingredient

        if (name != other.name) return false
        return img.contentEquals(other.img)
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + img.contentHashCode()
        return result
    }
}


data class Dish(
    val id: Int,
    val name: String,
    val descriptions: String,
    val price: Float,
    val discount: Float,
    val weight: Float,
    val image: ByteArray,
    val category: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.createByteArray()!!,
        parcel.readString()!!
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dish

        if (id != other.id) return false
        if (name != other.name) return false
        if (descriptions != other.descriptions) return false
        if (price != other.price) return false
        if (discount != other.discount) return false
        if (weight != other.weight) return false
        if (!image.contentEquals(other.image)) return false
        return category == other.category
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + descriptions.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + discount.hashCode()
        result = 31 * result + weight.hashCode()
        result = 31 * result + image.contentHashCode()
        result = 31 * result + category.hashCode()
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(descriptions)
        parcel.writeFloat(price)
        parcel.writeFloat(discount)
        parcel.writeFloat(weight)
        parcel.writeByteArray(image)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dish> {
        override fun createFromParcel(parcel: Parcel): Dish {
            return Dish(parcel)
        }

        override fun newArray(size: Int): Array<Dish?> {
            return arrayOfNulls(size)
        }
    }

}
