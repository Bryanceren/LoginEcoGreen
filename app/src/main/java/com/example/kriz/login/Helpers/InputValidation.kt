package com.example.kriz.login.Helpers

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.lang.Error


class InputValidation

    (private val context: Context) {


    fun isInputEditTextFilled(textInputEditText: EditText, message: String): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty()) {
            textInputEditText.setError(message)
            hideKeyboardFrom(textInputEditText)
            return false
        } else {

        }

        return true
    }



    fun isInputEditTextEmail(textInputEditText: EditText, message: String): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputEditText.setError(message)
            hideKeyboardFrom(textInputEditText)
            return false
        } else {
        }
        return true
    }


    fun isInputEditTextMatches(textInputEditText1: EditText, textInputEditText2: EditText, message: String): Boolean {
        val value1 = textInputEditText1.text.toString().trim()
        val value2 = textInputEditText2.text.toString().trim()
        if (!value1.contentEquals(value2)) {
            textInputEditText2.setError(message)
            textInputEditText1.setError(message)
            hideKeyboardFrom(textInputEditText2)
            return false
        } else {
        }
        return true
    }


    private fun hideKeyboardFrom(view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}