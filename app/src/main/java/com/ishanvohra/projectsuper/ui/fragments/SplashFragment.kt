package com.ishanvohra.projectsuper.ui.fragments

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ishanvohra.projectsuper.R
import com.ishanvohra.projectsuper.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        //display animation
        startAnimation()

        return binding.root
    }

    private fun startAnimation() {
        //animating the splashImage to go up and then fade away
        binding.splashImage.animate()
            .setDuration(1000)
            .translationY(-450f)
            .alpha(0f)
            .setStartDelay(2000)
            .setListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                }

                override fun onAnimationEnd(p0: Animator?) {
                    findNavController().navigate(R.id.homeFragment)
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationRepeat(p0: Animator?) {
                }

            })
            .start()

        binding.textView.animate()
            .setDuration(1000)
            .alpha(0f)
            .setStartDelay(1000)
            .start()
    }

}