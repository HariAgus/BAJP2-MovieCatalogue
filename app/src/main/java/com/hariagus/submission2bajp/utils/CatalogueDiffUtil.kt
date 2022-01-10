package com.hariagus.submission2bajp.utils

import androidx.recyclerview.widget.DiffUtil

class CatalogueDiffUtil<T>(
    private val oldList: List<T>, private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int, newItemPosition: Int
    ): Boolean = oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(
        oldItemPosition: Int, newItemPosition: Int
    ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]

}