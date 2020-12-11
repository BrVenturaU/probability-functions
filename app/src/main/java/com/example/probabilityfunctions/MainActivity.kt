package com.example.probabilityfunctions

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.probabilityfunctions.utils.BinomialHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spOpciones.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(position){
            1->{
                setOptionsVisibility(View.VISIBLE, btn=View.VISIBLE)
            }
            2->{
                setOptionsVisibility(op2=View.VISIBLE, btn=View.VISIBLE)
            }
            3->{
                setOptionsVisibility(op3=View.VISIBLE, btn=View.VISIBLE)
            }
            4->{
                setOptionsVisibility(op4=View.VISIBLE, btn=View.VISIBLE)
            }
            else->{
                setOptionsVisibility()
            }
        }
    }

    private fun setOptionsVisibility(op1: Int=View.GONE, op2:Int=View.GONE,
                                     op3:Int=View.GONE, op4:Int=View.GONE, btn:Int=View.GONE){
        opcionUno.visibility = op1
        opcionDos.visibility = op2
        opcionTres.visibility = op3
        opcionCuatro.visibility = op4
        btnCalcular.visibility = btn
    }
}