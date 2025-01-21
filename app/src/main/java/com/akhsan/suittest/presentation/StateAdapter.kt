package com.akhsan.suittest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akhsan.suittest.R
import com.akhsan.suittest.databinding.ItemStateBinding

class StateAdapter :
    LoadStateAdapter<StateAdapter.LoadingStateViewHolder>() {

    class LoadingStateViewHolder(private val binding: ItemStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.apply {
                if (loadState is LoadState.NotLoading && loadState.endOfPaginationReached) {
                    tvEmpty.text = root.context.getString(R.string.empty_item)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        return LoadingStateViewHolder(
            ItemStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}