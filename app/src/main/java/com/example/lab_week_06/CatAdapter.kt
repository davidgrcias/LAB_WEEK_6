package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<CatViewHolder>() {
    val swipeToDeleteCallback = SwipeToDeleteCallback()
    private val cats = mutableListOf<CatModel>()

    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll(newCats)

//        This is used to tell the adapter that there's a data change in the mutable list
        notifyDataSetChanged()
    }

//    A view holder is used to bind the data to the layout views
//    onCreateViewHolder is instantiating the view holder it self

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)

        return CatViewHolder(view, imageLoader, onClickListener)
    }

    //    This is used to get the amount of data/item in the list
    override fun getItemCount() = cats.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
//        The holder parameter stores our previously created ViewHolder
//        The holder.bindData function is declared in the CatViewHolder

        holder.bindData(cats[position])
    }

    fun removeItem(position: Int) {
        cats.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnClickListener {
        fun onItemClick(cat: CatModel)

    }

    inner class SwipeToDeleteCallback :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ) = if (viewHolder is CatViewHolder) {
//                        Here, if we're not touching our phone, left and right are allowed
            makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_DRAG,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//                            Here, if we're swiping our phone, left and right are allowed
            ) or makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_SWIPE,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            )
        } else {
            0
        }

        override fun onSwiped(
            viewHolder: RecyclerView.ViewHolder,
            direction: Int
        ) {
            val position = viewHolder.adapterPosition
            removeItem(position)
        }
    }
}