package com.example.mywallet.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallet.databinding.FragmentItemBinding
import com.example.mywallet.model.Activity

class ActivityAdapter( private val listener: OnItemClickListener) : ListAdapter<Activity, ActivityAdapter.ViewHolder>(DiffCallback)  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityAdapter.ViewHolder {

        val binding = FragmentItemBinding.inflate( LayoutInflater.from(parent.context) )

        return ViewHolder( binding )
    }
    override fun onBindViewHolder(holder: ActivityAdapter.ViewHolder, position: Int) {
        val activity = getItem( position )

        holder.textViewActivity.text = activity.name
        holder.onClickListener( getItem( holder.adapterPosition), listener )
    }

    class ViewHolder( val binding: FragmentItemBinding ) : RecyclerView.ViewHolder( binding.root ) {

        val textViewActivity: TextView

        init {
            textViewActivity = binding.textViewActivity
        }
        fun onClickListener(activity: Activity, listener: OnItemClickListener) {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick( activity )
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick( activity: Activity )
    }

    object DiffCallback : DiffUtil.ItemCallback<Activity>() {
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return  oldItem.id == newItem.id
        }

    }
}
