package br.com.philippesis.kmvvmv2.data.models

data class DeviceModel(val fabModel: String, val descModel: String, val fabYear: String, val manufacturer: String) {

    override fun toString(): String {
        return "fabModel: $fabModel | descModel: $descModel | fabYear: $fabYear | manufacturer: $manufacturer"
    }

}