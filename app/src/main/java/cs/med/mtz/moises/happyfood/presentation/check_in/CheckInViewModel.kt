package cs.med.mtz.moises.happyfood.presentation.check_in

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*

class CheckInViewModel : ViewModel() {



    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        callback: (Task<AuthResult>) -> Unit
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener {
            callback(it)
        }
    }


}