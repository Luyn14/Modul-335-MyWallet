package com.example.mywallet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mywallet.R
import com.example.mywallet.databinding.FragmentHomeBinding

class Home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.buttonWallet.setOnClickListener { view ->
            Navigation.findNavController( view ).navigate(R.id.action_home_to_wallet)
        }

        binding.buttonHistory.setOnClickListener { view ->
            Navigation.findNavController( view ).navigate(R.id.action_home_to_activityList)
        }



        // Inflate the layout for this fragment
        return binding.root
    }


}