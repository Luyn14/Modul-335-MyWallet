package com.example.mywallet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.mywallet.databinding.FragmentWalletBinding
import com.example.mywallet.model.Activity
import com.example.mywallet.model.Wallet
import kotlinx.android.synthetic.main.fragment_wallet.*


class Wallet : Fragment() {

    private val viewModel: WalletViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWalletBinding.inflate( inflater )

        val activity = arguments?.get( "activity" ) as Activity?
        val wallet = arguments?.get( "wallet" ) as Wallet?

        viewModel.setActivity( activity )

        viewModel.setWallet( wallet )

        viewModel.liveDataWallet.observe(viewLifecycleOwner) { newWallet ->
            moneyView.text = newWallet.value.toString()
        }

        binding.apply {

            eingabeName.addTextChangedListener {
                viewModel.setActivityName( it.toString() )
            }

            eingabeWert.addTextChangedListener {
                if ( it.toString().isNotBlank() ) {
                    viewModel.setActivityValue( it.toString().toLong() )
                }

            }

            buttonTranscation.setOnClickListener{
                viewModel.onSaveActivityClick( )
            }
        }
        return binding.root
    }

}