package com.example.testapp1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(private val dataSet: ArrayList<ArrayList<String>>, private val usersActivity: UsersActivity) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View, usersActivity: UsersActivity) : RecyclerView.ViewHolder(view) {
        val idView: TextView
        val nameView: TextView
        val surnameView: TextView
        val removeButton: Button

        init {
            // Define click listener for the ViewHolder's View.
            idView = view.findViewById(R.id.idView)
            nameView = view.findViewById(R.id.nameView)
            surnameView = view.findViewById(R.id.surnameView)
            removeButton = view.findViewById(R.id.removeButton)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.single_user, viewGroup, false)

        return ViewHolder(view, usersActivity)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.idView.text = dataSet[position][0]
        viewHolder.nameView.text = dataSet[position][1]
        viewHolder.surnameView.text = dataSet[position][2]
        viewHolder.removeButton.setOnClickListener{
            usersActivity.delUser(UsersActivity.URL, dataSet[position][0].toInt())
            this.usersActivity.showDataFromApi(UsersActivity.URL)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
