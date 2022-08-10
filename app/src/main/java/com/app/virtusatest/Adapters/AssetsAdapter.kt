package com.app.virtusatest.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.virtusatest.Interface.OnAssetsItemClickListener
import com.app.virtusatest.Model.assets
import com.app.virtusatest.R
import com.app.virtusatest.Utils.CommonFunctions

class AssetsAdapter(var context: Context?) :
    RecyclerView.Adapter<AssetsViewHolder>() {
    var list: List<assets> = ArrayList<assets>()
    var onItemClickListener: OnAssetsItemClickListener? = null
    var commonFunctions = CommonFunctions()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.assets_itemraw, parent, false)
        return AssetsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setResults(results: List<assets?>) {
        list = results as List<assets>
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AssetsViewHolder, position: Int) {
        var assets_raw = list.get(position)
        holder.txtid.text = assets_raw.asset_id
        holder.txtname.text = assets_raw.name
        holder.txtprice_usd.text = String.format("%.3f", assets_raw.price_usd)
        holder.txtstartdate.text = commonFunctions.Formatdate(assets_raw.data_start!!)
            .plus(context!!.getString(R.string.to))
            .plus(commonFunctions.Formatdate(assets_raw.data_end!!))
        if (assets_raw.type_is_crypto == 1) {
            holder.type_coin.text = context?.getString(R.string.cryptocurrency)
        } else {
            holder.type_coin.text = context?.getString(R.string.other)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener!!.onItemClick(assets_raw)
        }
    }
}

// inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class AssetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtname: TextView
    val txtid: TextView
    val txtprice_usd: TextView
    val txtstartdate: TextView
    val type_coin: TextView

    init {
        txtname = itemView.findViewById(R.id.txtname)
        txtid = itemView.findViewById(R.id.txtid)
        txtprice_usd = itemView.findViewById(R.id.txtprice_usd)
        txtstartdate = itemView.findViewById(R.id.txtstartdate)
        type_coin = itemView.findViewById(R.id.type_coin)

    }
}