package cs.med.mtz.moises.happyfood.presentation.login

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import cs.med.mtz.moises.happyfood.R
import cs.med.mtz.moises.happyfood.databinding.ActivityLoginBinding
import cs.med.mtz.moises.happyfood.presentation.main.MainActivity

class LoginActivity : AppCompatActivity() {
    /** */
    private val viewModel: LoginViewModel by viewModels()

    /**
     *
     */


    private val email: String
        get() = binding.etEmail.text.toString()

    private val password: String
        get() = binding.etPasword.text.toString()

    /**
     *
     */

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }


    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loginSetOnClick()

    }

    /**
     *
     */
    private fun loginSetOnClick() {
        binding.loginButton.setOnClickListener {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginWithEmailAndPassword(email, password, ::onLoginResult)
            } else alert(getString(R.string.login_email_or_pasword_is_empty))
        }
    }

    /** */
    private fun onLoginResult(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            showHome()
        } else {
            val errorMessage = when (task.exception) {
                is FirebaseAuthInvalidUserException ->
                    getString(R.string.login_user_not_registered_exeption)
                is FirebaseAuthInvalidCredentialsException ->
                    getString(R.string.login_invalid_credentials_exception)
                is FirebaseTooManyRequestsException ->
                    getString(R.string.login_user_invalid_pasword_exception)
                is FirebaseException ->
                    getString(R.string.login_no_wifi_exeption)
                else -> getString(R.string.login_email_or_pasword_is_empty)
            }
            alert(errorMessage)
        }
    }

    /**
     *
     */

    private fun showHome() {
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(homeIntent)

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

}