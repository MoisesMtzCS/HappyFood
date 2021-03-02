package cs.med.mtz.moises.happyfood.presentation.check_in

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import cs.med.mtz.moises.happyfood.R
import cs.med.mtz.moises.happyfood.databinding.ActivityCheckInBinding
import cs.med.mtz.moises.happyfood.databinding.ActivityCheckInBindingImpl
import cs.med.mtz.moises.happyfood.presentation.login.LoginActivity
import cs.med.mtz.moises.happyfood.presentation.main.MainActivity
import cs.med.mtz.moises.happyfood.presentation.splash_screen.SplashActivity

class CheckInActivity : AppCompatActivity() {
    /** */

    private val name: String
        get() = binding.etName.text.toString()

    private val email: String
        get() = binding.etEmail.text.toString()

    private val password: String
        get() = binding.etPasword.text.toString()

    /** */

    private val binding: ActivityCheckInBinding by lazy {
        ActivityCheckInBinding.inflate(
            layoutInflater
        )
    }

    /** */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        signUpSetOnClick()
        goToLogin()
    }

    private fun signUpSetOnClick() {
        binding.signUpButton.setOnClickListener {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    email,
                    password
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "")
                    } else {
                        showAlert()
                    }
                }
            } else {
                showAlert()
            }
        }
    }

    private fun goToLogin() {
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.Error))
            .setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String) {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }
}