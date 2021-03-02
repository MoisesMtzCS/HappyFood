package cs.med.mtz.moises.happyfood.presentation.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import cs.med.mtz.moises.happyfood.R
import cs.med.mtz.moises.happyfood.presentation.app_intro.AppIntroActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** */
class SplashActivity : AppCompatActivity() {

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToAfterSplashTime()
    }

    /** */
    private fun navigateToAfterSplashTime() {
        val intent = Intent(this, AppIntroActivity::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            delay(1200)
            startActivity(intent)
            finish()
        }
    }

}