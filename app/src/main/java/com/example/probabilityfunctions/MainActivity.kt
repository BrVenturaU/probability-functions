package com.example.probabilityfunctions

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.probabilityfunctions.utils.BinomialHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.NumberFormatException

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
        try {
            validateInputData(etN.text.toString(), etN, false,"El valor de n es requerido.")
            validateInputData(etProbabilidad.text.toString(), etProbabilidad, false,"El valor de p es requerido.")
            validateInputData(editText.text.toString(), editText, false,"El valor del evento x es requerido.")

            val x = editText.text.toString().toInt()
            val isValueIncluded = switch.isChecked
            val n = etN.text.toString().toInt()
            val p = etProbabilidad.text.toString().toFloat()
            val result = if (position == 2) BinomialHelper.probabilidadBinomialAcumulada(n, p, fin = x, incluirFin = isValueIncluded)
            else BinomialHelper.probabilidadBinomialAcumulada(n, p, inicio = x, incluirInicio = isValueIncluded)
            setResultData(result.toString())
        }catch (ne: NumberFormatException){
            Toast.makeText(this, ne.message, Toast.LENGTH_SHORT).show()
        }catch (ex: Exception){
            Toast.makeText(this, "Error interno, por favor comuniquese con los desarrolladores.", Toast.LENGTH_LONG).show()
        }
    }

    private fun calculateWithRange(){

        try {
            validateInputData(etN.text.toString(), etN, false,"El valor de n es requerido.")
            validateInputData(etProbabilidad.text.toString(), etProbabilidad,false, "El valor de p es requerido.")
            validateInputData(etEventoCuatroA.text.toString(), etEventoCuatroA, false,"El primer valor (a) es requerido.")
            validateInputData(etEventoCuatroB.text.toString(), etEventoCuatroB, false, "El segundo valor (b) es requerido.")

            val xA = etEventoCuatroA.text.toString().toInt()
            val xB = etEventoCuatroB.text.toString().toInt()
            val isAIncluded = cbxIncluirA.isChecked
            val isBIncluded = cbxIncluirB.isChecked
            val n = etN.text.toString().toInt()
            val p = etProbabilidad.text.toString().toFloat()
            val result = BinomialHelper.probabilidadBinomialAcumulada(n,p,xA,xB, isAIncluded,isBIncluded)
            setResultData(result.toString())
        }catch (ne: NumberFormatException){
            Toast.makeText(this, ne.message, Toast.LENGTH_SHORT).show()
        }catch (ex: Exception){
            Toast.makeText(this, "Error interno, por favor comuniquese con los desarrolladores.", Toast.LENGTH_LONG).show()
        }
    }

    private fun setResultData(resultValue: String){
        tvProbabilidad.text = resultValue
        tvProbabilidadContraria.text = " ${BinomialHelper.probabilidadContraria(resultValue.toFloat())}"
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
            editText.error = null
        }
    }

    private fun validateInputData(value: String, editText: EditText, hasException: Boolean=true,
                                  mensaje:String="Error de validación."){

        if(value.isNullOrEmpty() && hasException){
            editText.error = mensaje
            throw NumberFormatException(mensaje)
        }
        else if (value.isNullOrEmpty() && !hasException){
            editText.error = mensaje
            throw NumberFormatException("Error de validación.")
        }
    }
}