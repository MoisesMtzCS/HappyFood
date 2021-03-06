package cs.med.mtz.moises.happyfood.presentation.splash_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/** */
class SplashViewModel : ViewModel() {

    val user = Firebase.auth.currentUser



}