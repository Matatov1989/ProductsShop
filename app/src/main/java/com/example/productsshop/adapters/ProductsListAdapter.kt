package com.example.productsshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productsshop.R
import com.example.productsshop.models.ProductItem

class ProductsListAdapter(private val products: List<ProductItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val SALE_VIEW = 1
        private const val BASE_VIEW = 2
    }

    class ViewHolderSale(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProductSale: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewProductNameSale: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewProductShortDescriptionSale: TextView = itemView.findViewById(R.id.textViewProductShortDescription)
        val ratingProductSale: RatingBar = itemView.findViewById(R.id.ratingProduct)
        val textViewProductPriceSale: TextView = itemView.findViewById(R.id.textViewProductPrice)
    }

    class ViewHolderBase(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProductBase: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewProductNameBase: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewProductShortDescriptionBase: TextView = itemView.findViewById(R.id.textViewProductShortDescription)
        val ratingProductBase: RatingBar = itemView.findViewById(R.id.ratingProduct)
        val textViewProductPriceBase: TextView = itemView.findViewById(R.id.textViewProductPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SALE_VIEW -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_product_sale, parent, false)
                ViewHolderSale(view)
            }

            BASE_VIEW -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_product_base, parent, false)
                ViewHolderBase(view)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderSale -> {
//                holder.imageViewProductSale.setImageBitmap(products[position].imageUrl)
                holder.textViewProductNameSale.text = products[position].name
                holder.textViewProductShortDescriptionSale.text = products[position].shortDescription
                holder.ratingProductSale.rating = products[position].rating
                holder.textViewProductPriceSale.text = "${products[position].price} $"
            }

            is ViewHolderBase -> {
//                holder.imageViewProductBase.setImageBitmap(products[position].imageUrl)
                holder.textViewProductNameBase.text = products[position].name
                holder.textViewProductShortDescriptionBase.text = products[position].shortDescription
                holder.ratingProductBase.rating = products[position].rating
                holder.textViewProductPriceBase.text = "${products[position].price} $"
            }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun getItemViewType(position: Int): Int {
        val discount = products[position].discount
        return if (discount > 0) SALE_VIEW else BASE_VIEW
    }
}
