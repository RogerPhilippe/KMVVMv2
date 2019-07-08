package br.com.philippesis.kmvvmv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.philippesis.kmvvmv2.ui.device.ModelsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent(this, ModelsActivity::class.java)
        startActivity(intent)

    }
}
