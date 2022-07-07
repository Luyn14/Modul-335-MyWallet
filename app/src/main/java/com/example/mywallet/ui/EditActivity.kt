package com.example.mywallet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.mywallet.R
import com.example.mywallet.databinding.FragmentActivityListBinding
import com.example.mywallet.databinding.FragmentEditActivityBinding
import com.example.mywallet.databinding.FragmentWalletBinding
import com.example.mywallet.model.Activity
import com.google.android.material.snackbar.Snackbar

class EditActivity : Fragment() {

    private lateinit var binding: FragmentEditActivityBinding

    val viewModel: EditActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentEditActivityBinding.inflate( inflater )

        val activity = arguments?.get( "activity" ) as Activity?

        viewModel.setActivity( activity )

        viewModel.liveDataActivity.observe( viewLifecycleOwner ) { activity ->
            binding.apply {
                eingabeName.setText( activity.name)
                eingabeWert.setText( activity.value.toString())

            }
        }



        binding.apply {

            eingabeName.addTextChangedListener {
                viewModel.setActivityName( it.toString() )
            }

            eingabeWert.addTextChangedListener {
                if(it.toString().isNotEmpty()) {
                    viewModel.setActivityValue(it.toString().toLong())
                }
            }

            buttonTranscation.setOnClickListener{
                viewModel.onSaveActivityClick( )
            }

            buttonDelete.setOnClickListener{
                viewModel.onDeleteQuestionClicker()

                Navigation.findNavController(it).navigate( R.id.action_editActivity_to_activityList)
            }
        }

        return binding.root
    }


}



