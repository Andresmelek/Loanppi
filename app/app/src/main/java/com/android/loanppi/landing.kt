package com.android.loanppi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.*

class landing : AppCompatActivity() {

    var type: String = ""
    var label: String = ""
    var btn1: String = ""
    var porc: Int = 0
    var meta: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
    }

    fun onSignUp(view: View) {
        val intent = Intent(this, signup::class.java)
        startActivity(intent)
    }

    fun onLogin(view: View) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_login, null)
        val title = dialogLayout.findViewById<TextView>(R.id.txt_login_title)
        val btn_f = dialogLayout.findViewById<Button>(R.id.btn_l_facebook)
        val btn_g = dialogLayout.findViewById<Button>(R.id.btn_l_google)
        builder.setView(dialogLayout)
        builder.setNegativeButton(R.string.cancelar) { dialog, which ->
            Toast.makeText(applicationContext,
                R.string.cancelar, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    fun onFb(view: View) {
        type = "Inversor"
        label = "Retorno"
        btn1 = "Mi inversión"
        porc = 40000
        meta = 120000F
        onDashboard(view)
    }

    fun onGo(view: View) {
        type = "Trabajador"
        label = "Meta"
        btn1 = "Mi préstamo"
        porc = 350000
        meta = 800000F
        onDashboard(view)
    }

    fun onDashboard(view: View) {
        val intent = Intent(this, dashboard::class.java)
        intent.putExtra("type", "Hola, "+type)
        intent.putExtra("label", label)
        intent.putExtra("btn1", btn1)
        intent.putExtra("porc", porc)
        intent.putExtra("meta", meta)
        startActivity(intent)
    }
}

