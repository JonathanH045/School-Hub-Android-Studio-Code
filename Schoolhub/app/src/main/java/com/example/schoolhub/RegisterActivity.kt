package com.example.schoolhub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.compose.ui.semantics.text
import android.graphics.Typeface
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import androidx.compose.ui.geometry.isEmpty
import androidx.compose.ui.semantics.error
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.color
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val alracc = findViewById<TextView>(R.id.alracc) // Assuming alracc is the ID of your TextView
        val textacc = SpannableString("Log in") // Text with hyperlink

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Intent to navigate to LoginActivity
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#4998FF")
                ds.isUnderlineText = true
            }
        }

// Apply the clickable span to the "Login" part of the text
        textacc.setSpan(clickableSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        alracc.text = textacc
        alracc.movementMethod = LinkMovementMethod.getInstance()

        val textView = findViewById<TextView>(R.id.textRegister)
        val text = SpannableString("SCHOOL\nHUB")

        text.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0, 6,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        text.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            6, 10,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val emailTextInputLayout = findViewById<EditText>(R.id.inputEmail)
        val email = emailTextInputLayout.text.toString()

        if (email.isEmpty()) {
            emailTextInputLayout.error = "Email cannot be empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextInputLayout.error = "Invalid email format"
        } else {
            emailTextInputLayout.error = null // Clear any previous errors
            // Proceed with using the valid email
        }
        textView.text = text
        val typeface = ResourcesCompat.getFont(this, R.font.inter_extrabold)
        textView.typeface = typeface
        val buttonSignUp = findViewById<Button>(R.id.btnLogin)
        buttonSignUp.setOnClickListener {
            val username = findViewById<EditText>(R.id.inputUsername).text.toString()
            val email = findViewById<EditText>(R.id.inputEmail).text.toString()
            val nim = findViewById<EditText>(R.id.inputNIM).text.toString()
            val password = findViewById<EditText>(R.id.inputPassword).text.toString()
            val passwordConfirmation = findViewById<EditText>(R.id.inputPasswordcn).text.toString()

            // Input Validation
            if (username.isEmpty() || email.isEmpty() || nim.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != passwordConfirmation) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Account Creation Logic (using Shared Preferences)
            val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.putString("email", email)
            editor.putString("nim", nim)
            editor.putString("password", password) // Consider hashing the password for security
            editor.apply()

            Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}