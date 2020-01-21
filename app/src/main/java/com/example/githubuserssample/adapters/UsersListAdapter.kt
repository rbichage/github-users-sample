package com.example.githubuserssample.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserssample.R
import com.example.githubuserssample.activities.UserDetailsActivity
import com.example.githubuserssample.models.User
import com.google.android.material.card.MaterialCardView
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.layout_user_item.view.*

class UsersListAdapter(private val usersList: List<User>, private val context: Context) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]
        holder.itemView.username.text = user.login

        Glide.with(context)
                .load(user.avatarUrl)
                .into(holder.itemView.user_avi)

        holder.itemView.rootlayout.setOnClickListener { v: View? ->
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra("user", user)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = usersList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}