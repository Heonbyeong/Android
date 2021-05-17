package com.example.calculator_mvvm.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

class MainViewModel : BaseObservable() {
    var currentInput = ObservableField<String>()
    var tempInput = ObservableField<String>()
    var latestInput = ObservableField<String>()
    var latestResult = ObservableField<String>()

    private var first  = 0.0
    private var second = 0.0
    private var third = 0.0
    private var operator = 0
    private var stringBuffer = StringBuffer()
    private var newOperation = false
    private var commaActivated = false

    fun inputNumber(number: Int){
        if(currentInput.get() != "0" ){
            if(number == COMMA){
                if(!commaActivated && !currentInput.get().isNullOrBlank()){
                    commaActivated = true
                    stringBuffer.append(".")
                }
            } else {
                stringBuffer.append(number)
            }
        } else{
            if(number == COMMA && !commaActivated && !currentInput.get().isNullOrBlank()){
                commaActivated = true
                stringBuffer.append(".")
            } else{
                stringBuffer.delete(0, stringBuffer.capacity())
                stringBuffer.append(number)
            }
        }

        currentInput.set(stringBuffer.toString())
    }

    fun doAC(){
        stringBuffer.delete(0, stringBuffer.capacity())
        currentInput.set("")
        tempInput.set("")
        latestResult.set("")
        operator = 0
        commaActivated = false
    }

    fun doOperation(operation: Int){
        latestInput.set(stringBuffer.toString())
        tempInput.set(stringBuffer.toString())
        stringBuffer.delete(0, stringBuffer.capacity())
        currentInput.set("")
        operator = operation
        newOperation = true
        commaActivated = false
    }

    fun getTheResult() {
        if (operator != 0 && !currentInput.get().isNullOrBlank()) {

            if (!latestResult.get().isNullOrBlank()) {

                if (!newOperation) {
                    first = latestResult.get()!!.toDouble()
                    second = third
                } else {
                    first = latestResult.get()!!.toDouble()
                    second = currentInput.get()!!.toDouble()
                    third = second
                }

            } else {
                first = latestInput.get()!!.toDouble()
                second = currentInput.get()!!.toDouble()
                third = second
            }

            with (currentInput) {
                when (operator) {
                    PLUS -> set(first.plus(second).toString())
                    MINUS -> set(first.minus(second).toString())
                    MULTIPLY -> set(first.times(second).toString())
                    DIVISION -> set(first.div(second).toString())
                }
            }

            latestResult.set(currentInput.get().toString())
        }

        newOperation = false
    }

    companion object {
        const val PLUS = 1
        const val MINUS = 2
        const val MULTIPLY = 3
        const val DIVISION = 4
        const val COMMA = -1
    }
}