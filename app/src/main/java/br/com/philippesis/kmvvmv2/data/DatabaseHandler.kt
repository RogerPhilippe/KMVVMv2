package br.com.philippesis.kmvvmv2.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.philippesis.kmvvmv2.data.enuns.DeviceModelEnum

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    private val createDevicesTable = "CREATE TABLE ${DeviceModelEnum.TABLE_NAME.value} " +
            "(${DeviceModelEnum.ID.value} Integer PRIMARY KEY, ${DeviceModelEnum.FAB_MODEL.value} TEXT, " +
            "${DeviceModelEnum.DESC_MODEL.value} TEXT, ${DeviceModelEnum.YEAR_FAB.value} TEXT, " +
            "${DeviceModelEnum.MANUFACTURE.value} TEXT)"

    private var sqlCreateTablesArray = listOf(createDevicesTable)

    override fun onCreate(db: SQLiteDatabase?) {
        var count = 0
        do {
            db?.execSQL(sqlCreateTablesArray[count])
            count++
        } while (count < sqlCreateTablesArray.size)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        private const val DB_NAME = "devices_db"
        private const val DB_VERSION = 1
    }

}
