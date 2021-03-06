package cs.med.mtz.moises.happyfood.presentation.login

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/** */
class LoginViewModel : ViewModel() {

    /** */
    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        callback: (Task<AuthResult>) -> Unit
    ) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                callback(it)
            }
    }

}






