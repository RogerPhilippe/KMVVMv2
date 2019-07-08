package br.com.philippesis.kmvvmv2.ui.device

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.philippesis.kmvvmv2.R
import br.com.philippesis.kmvvmv2.data.models.DeviceModel

class DeviceListAdapter(private val list: ArrayList<DeviceModel>, private val context: Context):
    RecyclerView.Adapter<DeviceListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_devices, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.fabModel.text = list[position].fabModel
        holder.modelName.text = list[position].descModel
        holder.yearFab.text = list[position].fabYear
        holder.manufacBrand.text = list[position].manufacturer

    }

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val fabModel: TextView = view.findViewById(R.id.fab_model_list_id)
        val modelName: TextView = view.findViewById(R.id.model_name_list_id)
        val yearFab: TextView = view.findViewById(R.id.year_fab_list_id)
        val manufacBrand: TextView = view.findViewById(R.id.manufacture_brand_list_id)

    }

}