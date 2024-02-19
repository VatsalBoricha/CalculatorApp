package com.example.assignment2

import android.view.View
import com.example.assignment2.databinding.ActivityMainBinding

class Calculator(binding: ActivityMainBinding)
{
    private var m_resultLabelValue: String
    private var m_binding: ActivityMainBinding
    private var m_lhs: String
    private var m_active_operation: String

    init
    {
        this.m_binding = binding
        this.m_resultLabelValue = ""
        this.m_lhs = ""
        this.m_active_operation = ""

        initializeOnClickListeners()
    }

    private fun initializeOnClickListeners()
    {
        // operator buttons
        this.m_binding.multiplyButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.divideButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.addButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.subtractButton.setOnClickListener { view -> processOperatorButtons(view) }

        // extra buttons
        this.m_binding.clearButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.backspaceButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.percentButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.plusMinusButton.setOnClickListener { view -> processExtraButtons(view) }

        // number buttons
        this.m_binding.zeroButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.oneButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.twoButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.threeButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.fourButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.fiveButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.sixButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.sevenButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.eightButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.nineButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.decimalButton.setOnClickListener { view -> processNumberButtons(view) }
    }

    private fun processOperatorButtons(view: View)
    {
        if(m_lhs.isEmpty() && this.m_resultLabelValue.isNotEmpty())
        {
            this.m_lhs = this.m_resultLabelValue
            this.m_resultLabelValue = ""
            this.m_active_operation = view.tag.toString()
        }
        else if(this.m_lhs.isNotEmpty() && this.m_resultLabelValue.isEmpty())
        {
            this.m_active_operation = view.tag.toString()
        }
        else if(this.m_lhs.isNotEmpty() && this.m_resultLabelValue.isNotEmpty())
        {

            when(this.m_active_operation) {
                "multiply" -> {
                    this.m_lhs = multiply(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.resultTextView.text = this.m_lhs;
                }

                "divide" -> {
                    this.m_lhs = divide(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.resultTextView.text = this.m_lhs;
                }

                "add" -> {
                    this.m_lhs = add(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.resultTextView.text = this.m_lhs;
                }

                "subtract" -> {
                    this.m_lhs = subtract(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.resultTextView.text = this.m_lhs;
                }

                "equals" -> {

                }

                }

            this.m_active_operation = view.tag.toString()
        }

    }

    private fun processExtraButtons(view: View)
    {
        when(view.tag.toString())
        {
            "backspace" ->
            {
                this.m_resultLabelValue = this.m_resultLabelValue.dropLast(1)
                this.m_binding.resultTextView.text = this.m_resultLabelValue
                if(this.m_resultLabelValue.isEmpty() || this.m_resultLabelValue == "-")
                {
                    this.m_resultLabelValue = ""
                    this.m_binding.resultTextView.text = "0"
                }
            }
            "clear" ->
            {
                clear()
            }
            "plus_minus" ->
            {
                if(this.m_resultLabelValue.isNotEmpty())
                {
                    if(this.m_resultLabelValue.contains("-"))
                    {
                        this.m_resultLabelValue = this.m_resultLabelValue.removePrefix("-")
                    }
                    else
                    {
                        this.m_resultLabelValue = "-" + this.m_resultLabelValue
                    }
                    this.m_binding.resultTextView.text = this.m_resultLabelValue
                }
            }
            "percent" -> {
                if (this.m_resultLabelValue.isNotEmpty()) {
                    val currentValue = this.m_resultLabelValue.toFloat()

                    if (this.m_lhs.isNotEmpty()) {
                        val percentValue = this.m_lhs.toFloat() * (currentValue / 100.0)
                        this.m_resultLabelValue = percentValue.toString()
                    } else {
                        // If m_lhs is empty, assume the percentage of the current value
                        val percentValue = currentValue / 100.0
                        this.m_resultLabelValue = percentValue.toString()
                    }

                    this.m_binding.resultTextView.text = this.m_resultLabelValue
                }
            }
        }
    }

    private fun processNumberButtons(view: View)
    {
        when (view.tag.toString())
        {
            "." -> {
                if(!this.m_resultLabelValue.contains("."))
                {
                    if(this.m_resultLabelValue.isEmpty())
                    {
                        this.m_resultLabelValue = "0."
                    }
                    else
                    {
                        this.m_resultLabelValue += view.tag.toString()
                    }
                }
            }
            else -> {
                this.m_resultLabelValue += view.tag.toString()
            }
        }

        this.m_binding.resultTextView.text = this.m_resultLabelValue
    }

    private fun clear():Unit
    {
        this.m_resultLabelValue = ""
        this.m_lhs = ""
        this.m_active_operation = ""
        this.m_binding.resultTextView.text = "0"
    }

    private fun subtract(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() - RHS.toFloat()).toString()
        }
        return (LHS.toInt() - RHS.toInt()).toString()
    }

    private fun add(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() + RHS.toFloat()).toString()
        }
        return (LHS.toInt() + RHS.toInt()).toString()
    }

    private fun divide(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty() || RHS.contains("0"))
        {
            return "Error"
        }

        val result = LHS.toFloat()/ RHS.toFloat()

        return if (result % 1 == 0.0f){
            result.toInt().toString()
        }else {
            String.format("%.8f", result).removeSuffix( "0").removeSuffix("0")
        }
    }

    private fun multiply(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() * RHS.toFloat()).toString()
        }
        return (LHS.toInt() * RHS.toInt()).toString()
    }
}

