package br.com.philippesis.kmvvmv2.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.philippesis.kmvvmv2.data.models.DeviceModel

class DeviceModelViewModel: ViewModel(){

    private val devicesCurrent = MutableLiveData<ArrayList<DeviceModel>>()

    fun devicesCurrent(): MutableLiveData<ArrayList<DeviceModel>> {
        return devicesCurrent
    }

}