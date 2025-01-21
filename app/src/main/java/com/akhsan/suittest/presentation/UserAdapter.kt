package com.akhsan.suittest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akhsan.suittest.databinding.ItemRowUserBinding
import com.akhsan.suittest.domain.UserModel
import com.bumptech.glide.Glide

class UserAdapter :
    PagingDataAdapter<UserModel, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: UserModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.apply {
            Glide.with(itemView.context)
                .load(user?.avatar)
                .centerCrop()
                .into(ivAvatar)
            (user?.firstName + " " + user?.lastName).also { tvUsername.text = it }
            tvEmail.text = user?.email

            itemView.setOnClickListener {
                if (user != null) {
                    onItemClickCallback.onItemClicked(user)
                }
            }
        }
    }

    inner class ViewHolder(binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivAvatar = binding.ivAvatar
        val tvUsername = binding.tvUsername
        val tvEmail = binding.tvEmail
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserModel,
                newItem: UserModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}