package cs.med.mtz.moises.happyfood.presentation.app_intro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import cs.med.mtz.moises.happyfood.R
import cs.med.mtz.moises.happyfood.presentation.check_in.CheckInActivity

/** */
class AppIntroActivity : AppIntro() {
    /** */


    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startIntro()
    }

    private fun startIntro() {
        addSlide(
            AppIntroFragment.newInstance(
                title = getString(R.string.welcome_intro),
                description = getString(R.string.description_intro),
                R.drawable.ic_merca,
                Color.GRAY
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = getString(R.string.start_intro),
                description = getString(R.string.instruction_intro),
                R.drawable.ic_marijuana,
                Color.GRAY
            )
        )
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        val intent = Intent(this, CheckInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this, CheckInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

    }

}