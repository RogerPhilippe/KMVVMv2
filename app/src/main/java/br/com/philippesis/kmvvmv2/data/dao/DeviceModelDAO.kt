package br.com.philippesis.kmvvmv2.data.dao

import android.content.ContentValues
import android.content.Context
import br.com.philippesis.kmvvmv2.data.DatabaseHandler
import br.com.philippesis.kmvvmv2.data.DeviceModelViewModel
import br.com.philippesis.kmvvmv2.data.enuns.DeviceModelEnum
import br.com.philippesis.kmvvmv2.data.models.DeviceModel

class DeviceModelDAO {

    private var dbHandler: DatabaseHandler? = null

    fun addDevice(context: Context, deviceModel: DeviceModel): Boolean {
        dbHandler = DatabaseHandler(context)
        val db = dbHandler!!.writableDatabase
        val values = ContentValues()
        values.put(DeviceModelEnum.FAB_MODEL.value, deviceModel.fabModel)
        values.put(DeviceModelEnum.DESC_MODEL.value, deviceModel.descModel)
        values.put(DeviceModelEnum.YEAR_FAB.value, deviceModel.fabYear)
        values.put(DeviceModelEnum.MANUFACTURE.value, deviceModel.manufacturer)
        val success = db.insert(DeviceModelEnum.TABLE_NAME.value, null, values)
        db.close()
        return (Integer.parseInt("$success") != -1)
    }

    fun getAllDevicesModel(context: Context, viewModel: DeviceModelViewModel) {
        dbHandler = DatabaseHandler(context)
        val deviceList = ArrayList<DeviceModel>()
        val db = dbHandler!!.readableDatabase
        val selectAllQuery = "SELECT * FROM ${DeviceModelEnum.TABLE_NAME.value}"
        val cursor = db.rawQuery(selectAllQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val deviceModel = DeviceModel(
                        cursor.getString(cursor.getColumnIndex(DeviceModelEnum.FAB_MODEL.value)),
                        cursor.getString(cursor.getColumnIndex(DeviceModelEnum.DESC_MODEL.value)),
                        cursor.getString(cursor.getColumnIndex(DeviceModelEnum.YEAR_FAB.value)),
                        cursor.getString(cursor.getColumnIndex(DeviceModelEnum.MANUFACTURE.value))
                    )
                    deviceList.add(deviceModel)
                } while (cursor.moveToNext())
                viewModel.devicesCurrent().value = deviceList
            }
        }

    }

}