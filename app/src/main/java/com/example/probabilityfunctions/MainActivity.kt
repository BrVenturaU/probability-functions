package com.example.probabilityfunctions

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.probabilityfunctions.utils.BinomialHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spOpciones.onItemSelectedListener = this
        btnCalcular.setOnClickListener(this)
        btnLimpiar.setOnClickListener(this)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(position){
            1->{
                setOptionsVisibility(View.VISIBLE, buttons=View.VISIBLE)
            }
            2->{
                setOptionsVisibility(op2=View.VISIBLE, buttons=View.VISIBLE)
            }
            3->{
                setOptionsVisibility(op3=View.VISIBLE, buttons=View.VISIBLE)
            }
            4->{
                setOptionsVisibility(op4=View.VISIBLE, buttons=View.VISIBLE)
            }
            else->{
                setOptionsVisibility()
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            btnCalcular.id ->{
                when(spOpciones.selectedItemPosition){
                    1->{

                    }
                    2->{
                        calculateWithSpinner(etEventoDos, swOpcionDos, 2)
                    }
                    3->{
                        calculateWithSpinner(etEventoTres, swOpcionTres, 3)
                    }
                    4->{
                        calculateWithRange()
                    }
                }
            }

            btnLimpiar.id->{
                cleanInputs(arrayOf(etN, etProbabilidad, etEventoUno, etEventoDos, etEventoTres, etEventoCuatroA, etEventoCuatroB))
            }
        }
    }

    private fun calculateWithSpinner(editText: EditText, switch: Switch, position: Int){
        val x = editText.text.toString().toInt()
        val isValueIncluded = switch.isChecked
        val n = etN.text.toString().toInt()
        val p = etProbabilidad.text.toString().toFloat()
        val result = if (position == 2) BinomialHelper.probabilidadBinomialAcumulada(n, p, fin = x, incluirFin = isValueIncluded)
                        else BinomialHelper.probabilidadBinomialAcumulada(n, p, inicio = x, incluirInicio = isValueIncluded)
        tvProbabilidad.text = "${result}"
        tvProbabilidadContraria.text = " ${BinomialHelper.probabilidadContraria(result.toFloat())}"
        cvResultado.visibility = View.VISIBLE
    }

    private fun calculateWithRange(){
        val xA = etEventoCuatroA.text.toString().toInt()
        val xB = etEventoCuatroB.text.toString().toInt()
        val isAIncluded = cbxIncluirA.isChecked
        val isBIncluded = cbxIncluirB.isChecked
        val n = etN.text.toString().toInt()
        val p = etProbabilidad.text.toString().toFloat()
        val result = BinomialHelper.probabilidadBinomialAcumulada(n,p,xA,xB, isAIncluded,isBIncluded)
        tvProbabilidad.text = "${result}"
        tvProbabilidadContraria.text = " ${BinomialHelper.probabilidadContraria(result.toFloat())}"
        cvResultado.visibility = View.VISIBLE
    }

    private fun setOptionsVisibility(op1: Int=View.GONE, op2:Int=View.GONE,
                                     op3:Int=View.GONE, op4:Int=View.GONE,
                                     buttons:Int=View.GONE, card:Int=View.GONE){
        opcionUno.visibility = op1
        opcionDos.visibility = op2
        opcionTres.visibility = op3
        opcionCuatro.visibility = op4
        btnOptions.visibility = buttons
        cvResultado.visibility = card
    }

    private fun cleanInputs(editTexts: Array<EditText>){
        cvResultado.visibility = View.GONE
        tvProbabilidad.text = ""
        tvProbabilidadContraria.text = ""
        for (editText in editTexts){
            editText.text = null
        }
    }
}