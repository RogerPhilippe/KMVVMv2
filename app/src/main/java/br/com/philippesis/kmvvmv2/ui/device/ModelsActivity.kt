package br.com.philippesis.kmvvmv2.ui.device

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.philippesis.kmvvmv2.R
import br.com.philippesis.kmvvmv2.data.DeviceModelViewModel
import br.com.philippesis.kmvvmv2.data.dao.DeviceModelDAO
import br.com.philippesis.kmvvmv2.data.models.DeviceModel
import kotlinx.android.synthetic.main.activity_models.*

class ModelsActivity : AppCompatActivity() {

    private lateinit var deviceModelDAO: DeviceModelDAO

    private lateinit var adapter: RecyclerView.Adapter<DeviceListAdapter.ItemViewHolder>

    private var devices = arrayListOf<DeviceModel>()

    private lateinit var listDeviceModels: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_models)

        this.deviceModelDAO = DeviceModelDAO()

        val viewModel = ViewModelProviders.of(this).get(DeviceModelViewModel::class.java)

        viewModel.devicesCurrent().observe(this, Observer { devices ->

            if (devices != null && devices.isNotEmpty()) {
                this.devices = devices
                this.adapter.notifyDataSetChanged()
            }

        })

        setupContent(viewModel)
        setupListener()

    }

    private fun setupContent(viewModel: DeviceModelViewModel) {

        // RecyclerView
        this.listDeviceModels = findViewById(R.id.models_list)
        this.listDeviceModels.layoutManager = LinearLayoutManager(this)
        this.adapter = DeviceListAdapter(this.devices, this)
        this.listDeviceModels.adapter = this.adapter

        // Start LiveData
        this.deviceModelDAO.getAllDevicesModel(this, viewModel)

    }

    private fun setupListener() {

        // Add device button
        add_btn.setOnClickListener { setupDialodAddDevice() }

    }

    private fun setupDialodAddDevice() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.activity_add_devices)
        dialog.setCancelable(false)
        val fabModel: TextView = dialog.findViewById(R.id.add_device_txt_fab_model)
        val modelName: TextView = dialog.findViewById(R.id.add_device_txt_model_name)
        val yearFab: TextView = dialog.findViewById(R.id.add_device_year_fab)
        val manufBrand: TextView = dialog.findViewById(R.id.add_device_manufacture_brand)
        val cancelBtn: Button = dialog.findViewById(R.id.add_device_btn_cancel)
        val saveBtn: Button = dialog.findViewById(R.id.add_device_btn_save)
        cancelBtn.setOnClickListener { dialog.dismiss() }
        saveBtn.setOnClickListener {

            var status = false

            val valid = fabModel.text.toString().isNotEmpty() && modelName.text.toString().isNotEmpty() &&
                    yearFab.text.toString().isNotEmpty() && manufBrand.text.toString().isNotEmpty()

            if (valid) {

                val deviceModel = DeviceModel(fabModel.text.toString(), modelName.text.toString(),
                    yearFab.text.toString(), manufBrand.text.toString())

                status = this.deviceModelDAO.addDevice(this, deviceModel)

            }

            if (status) Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show()

        }
        dialog.show()
    }
}
