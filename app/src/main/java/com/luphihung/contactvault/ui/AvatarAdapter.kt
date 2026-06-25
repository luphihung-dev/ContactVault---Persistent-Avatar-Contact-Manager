package com.luphihung.contactvault.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luphihung.contactvault.databinding.ItemAvatarBinding

class AvatarAdapter(
    private val selectedName: String,
    private val onAvatarClick: (AvatarOption) -> Unit
) : RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val binding = ItemAvatarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AvatarViewHolder(binding)
    }

    override fun getItemCount(): Int = AvatarCatalog.avatars.size

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        holder.bind(AvatarCatalog.avatars[position])
    }

    inner class AvatarViewHolder(private val binding: ItemAvatarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(option: AvatarOption) = with(binding) {
            avatarImage.setImageResource(option.drawableRes)
            avatarLabel.text = option.label
            selectedIndicator.alpha = if (option.name == selectedName) 1f else 0f
            root.setOnClickListener { onAvatarClick(option) }
        }
    }
}
