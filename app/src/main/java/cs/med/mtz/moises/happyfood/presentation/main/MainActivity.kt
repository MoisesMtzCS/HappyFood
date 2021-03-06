package cs.med.mtz.moises.happyfood.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import cs.med.mtz.moises.happyfood.R
import cs.med.mtz.moises.happyfood.databinding.ActivityMainBinding
import cs.med.mtz.moises.happyfood.presentation.check_in.CheckInActivity
import cs.med.mtz.moises.happyfood.presentation.login.LoginActivity

class MainActivity : AppCompatActivity() {
    /**
     *
     */
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    /**
     *
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        signOut()
    }

    /**
     *
     */
    private fun signOut() {
        val intent = Intent(this, CheckInActivity::class.java)
        binding.signOff.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(intent)
        }

    }
}