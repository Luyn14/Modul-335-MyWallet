package com.example.mywallet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywallet.databinding.FragmentActivityListBinding
import com.example.mywallet.model.Activity


class ActivityList : Fragment(), ActivityAdapter.OnItemClickListener{

    private lateinit var binding: FragmentActivityListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewModel: ActivityListViewModel by viewModels()
        binding = FragmentActivityListBinding.inflate( inflater )
        val adapter = ActivityAdapter( this )

        binding.apply {
            recyclerViewActivity.adapter = adapter
            recyclerViewActivity.layoutManager = LinearLayoutManager( requireContext() )
            recyclerViewActivity.setHasFixedSize( true )

        }

        viewModel.getActivity().observe( viewLifecycleOwner) { activity ->
            adapter.submitList( activity )
        }
        return binding.root
    }

    override fun onItemClick(activity: Activity) {
        val action = ActivityListDirections.actionActivityListToEditActivity()
        action.setActivity( activity )
        Navigation.findNavController( binding.root ).navigate( action )

    }


}