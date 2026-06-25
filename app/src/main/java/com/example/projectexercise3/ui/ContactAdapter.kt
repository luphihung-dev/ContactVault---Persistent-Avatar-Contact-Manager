package com.example.projectexercise3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectexercise3.data.Contact
import com.example.projectexercise3.databinding.ItemContactBinding

class ContactAdapter(
    private val onContactClick: (Contact) -> Unit,
    private val onDeleteClick: (Contact) -> Unit
) : ListAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ContactViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) = with(binding) {
            avatarImage.setImageResource(AvatarCatalog.drawableFor(contact.avatarName))
            nameText.text = contact.name
            phoneText.text = contact.phoneNumber
            noteText.text = contact.note.ifBlank { root.context.getString(com.example.projectexercise3.R.string.no_note) }
            root.setOnClickListener { onContactClick(contact) }
            deleteButton.setOnClickListener { onDeleteClick(contact) }
        }
    }
}

private class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean = oldItem == newItem
}
