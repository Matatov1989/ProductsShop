package com.example.productsshop.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productsshop.R
import com.example.productsshop.models.ProductItem

class ProductsListAdapter(
    private val products: List<ProductItem>,
    val onItemClick: (ProductItem) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val SALE_VIEW = 1
        private const val BASE_VIEW = 2
    }

    class ViewHolderSale(itemView: View, private val onItemClick: (ProductItem) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val imageViewProductSale: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewProductNameSale: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewProductShortDescriptionSale: TextView = itemView.findViewById(R.id.textViewProductShortDescription)
        val ratingProductSale: RatingBar = itemView.findViewById(R.id.ratingProduct)
        val textViewProductPriceSale: TextView = itemView.findViewById(R.id.textViewProductPrice)
        val textViewProductNewPrice: TextView = itemView.findViewById(R.id.textViewProductNewPrice)

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(it.tag as ProductItem)
            }
        }
    }

    class ViewHolderBase(itemView: View, private val onItemClick: (ProductItem) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val imageViewProductBase: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewProductNameBase: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewProductShortDescriptionBase: TextView = itemView.findViewById(R.id.textViewProductShortDescription)
        val ratingProductBase: RatingBar = itemView.findViewById(R.id.ratingProduct)
        val textViewProductPriceBase: TextView = itemView.findViewById(R.id.textViewProductPrice)

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(it.tag as ProductItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SALE_VIEW -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_product_sale, parent, false)
                ViewHolderSale(view, onItemClick)
            }

            BASE_VIEW -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_product_base, parent, false)
                ViewHolderBase(view, onItemClick)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderSale -> {
                Glide
                    .with(holder.itemView)
                    .load(products[position].imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.baseline_android_24)
                    .into(holder.imageViewProductSale)

                holder.textViewProductNameSale.text = products[position].name
                holder.textViewProductShortDescriptionSale.text = products[position].shortDescription
                holder.ratingProductSale.rating = products[position].rating
                holder.textViewProductPriceSale.text = "${products[position].price} $"
                holder.textViewProductPriceSale.paintFlags = holder.textViewProductPriceSale.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                val newPrice = products[position].price - (products[position].price / products[position].discount)
                holder.textViewProductNewPrice.text = "${String.format("%.2f", newPrice)} $"
            }

            is ViewHolderBase -> {
                Glide
                    .with(holder.itemView)
                    .load(products[position].imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.baseline_android_24)
                    .into(holder.imageViewProductBase)

                holder.textViewProductNameBase.text = products[position].name
                holder.textViewProductShortDescriptionBase.text = products[position].shortDescription
                holder.ratingProductBase.rating = products[position].rating
                holder.textViewProductPriceBase.text = "${products[position].price} $"
            }
        }
        holder.itemView.tag = products[position]
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun getItemViewType(position: Int): Int {
        val discount = products[position].discount
        return if (discount > 0) SALE_VIEW else BASE_VIEW
    }
}
