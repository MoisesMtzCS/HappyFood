package cs.med.mtz.moises.happyfood.presentation.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import cs.med.mtz.moises.happyfood.R
import cs.med.mtz.moises.happyfood.presentation.app_intro.AppIntroActivity
import cs.med.mtz.moises.happyfood.presentation.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** */
class SplashActivity : AppCompatActivity() {

    /** */
    private val viewModel: SplashViewModel by viewModels()

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToAfterSplashTime()
    }

    /**
     *
     */

    private fun navigateToAfterSplashTime() {
        val user = viewModel.user
        val intent: Intent = Intent(
            this,
            if (user == null)
                AppIntroActivity::class.java
            else MainActivity::class.java
        )
        startActivityAfterSplash(intent)
        intent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    /**
     *
     */
    private fun startActivityAfterSplash(intent: Intent) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1200)
            startActivity(intent)
        }
    }
    /**
     *
     */




}