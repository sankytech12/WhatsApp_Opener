package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number:String="0"
        if(intent.action==Intent.ACTION_PROCESS_TEXT){
            number=intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if(number.isDigitsOnly()){
            startWhatsapp(number)
        }
        else{
            Toast.makeText(this,"Please Check the entered number",Toast.LENGTH_SHORT).show()
        }

    }

    private fun startWhatsapp(number: String) {
        val i=Intent(Intent.ACTION_VIEW)
        i.setPackage("com.whatsapp")
        val data:String=if(number[0]=='+'){
            number.substring(1)
        }
        else if(number.length==10){
            "+91"+number
        }
        else{
            number
        }
        i.data= Uri.parse("https://wa.me/$data")
        if(packageManager.resolveActivity(i,0)!=null){
            startActivity(i)
        }
        else{
            Toast.makeText(this,"Please install WhatsApp from appStore",Toast.LENGTH_SHORT).show()
        }
    }
}