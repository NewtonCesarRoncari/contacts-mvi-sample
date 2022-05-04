package com.picpay.desafio.android.presentation.ui.fragment.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.presentation.model.UserPresentation

class UserListDiffCallback(
    private val oldList: List<UserPresentation>,
    private val newList: List<UserPresentation>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username.equals(newList[newItemPosition].username)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}