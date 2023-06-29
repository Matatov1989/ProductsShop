package com.example.productsshop.models

import android.os.Parcel
import android.os.Parcelable

data class ProductItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val shortDescription: String,
    val longDescription: String,
    val price: Float,
    val discount: Int,
    val rating: Float,
    val quantity: Int,
    val color: List<ProductColor>
)  : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProductItem> = object : Parcelable.Creator<ProductItem> {
            override fun createFromParcel(source: Parcel): ProductItem = ProductItem(source)
            override fun newArray(size: Int): Array<ProductItem?> = arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readInt(),
        mutableListOf<ProductColor>().apply {
            parcel.readList(this, ProductColor::class.java.classLoader)
        }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(imageUrl)
        dest.writeString(shortDescription)
        dest.writeString(longDescription)
        dest.writeFloat(price)
        dest.writeInt(discount)
        dest.writeFloat(rating)
        dest.writeInt(quantity)
        dest.writeList(color)
    }

    override fun describeContents(): Int = 0

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
