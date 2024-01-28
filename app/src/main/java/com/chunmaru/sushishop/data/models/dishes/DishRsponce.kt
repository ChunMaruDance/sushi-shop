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
        )

    companion object {
        fun List<DishResponse>.toDishList(): List<Dish> {
            return map { it.toDish() }
        }
    }

}


data class TestDish(
    val id: Int,
    val name: String,
    val descriptions: String,
    val category: String,
    val price: Float,
    val discount: Float,
    val weight: Float,
    val image: Int,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(descriptions)
        parcel.writeString(category)
        parcel.writeFloat(price)
        parcel.writeFloat(discount)
        parcel.writeFloat(weight)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TestDish> {
        override fun createFromParcel(parcel: Parcel): TestDish {
            return TestDish(parcel)
        }

        override fun newArray(size: Int): Array<TestDish?> {
            return arrayOfNulls(size)
        }
    }
}


data class IngredientsData(
    val name: String,
    val img: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IngredientsData> {
        override fun createFromParcel(parcel: Parcel): IngredientsData {
            return IngredientsData(parcel)
        }

        override fun newArray(size: Int): Array<IngredientsData?> {
            return arrayOfNulls(size)
        }
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
) {
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
        return image.contentEquals(other.image)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + descriptions.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + discount.hashCode()
        result = 31 * result + weight.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }

}
