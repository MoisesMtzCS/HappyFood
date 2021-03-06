package cs.med.mtz.moises.happyfood.presentation.check_in

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
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

    private val viewModel: CheckInViewModel by viewModels()

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

    /**
     *
     */

    private fun signUpSetOnClick() {
        binding.signUpButton.setOnClickListener {
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.createUserWithEmailAndPassword(email, password, ::onCheckInResult)
            } else {
                alert(getString(R.string.sign_up_email_or_pasword_is_empty))
            }
        }
    }

    /**
     *
     */
    private fun onCheckInResult(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            showHome()
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            val errorMessage: String = when (task.exception) {
                is FirebaseAuthUserCollisionException ->
                    getString(R.string.sign_up_user_collision_exception)
                is FirebaseAuthInvalidCredentialsException ->
                    getString(R.string.sign_up_invalid_credentials_exception)
                is FirebaseException ->
                    getString(R.string.sign_up_no_wifi_exeption)
                is FirebaseAuthWeakPasswordException ->
                    getString(R.string.sign_up_weak_password_exeption)

                else -> getString(R.string.sign_up_default_error)
            }

            alert(errorMessage)
        }
    }

    /**
     *
     */

    private fun goToLogin() {
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     *
     */

    private fun alert(message: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /**
     *
     */

    private fun showHome() {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }
}