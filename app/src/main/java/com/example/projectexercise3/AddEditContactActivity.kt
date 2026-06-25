package com.example.projectexercise3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.projectexercise3.data.Contact
import com.example.projectexercise3.databinding.ActivityAddEditContactBinding
import com.example.projectexercise3.databinding.DialogAvatarPickerBinding
import com.example.projectexercise3.ui.AvatarAdapter
import com.example.projectexercise3.ui.AvatarCatalog
import com.example.projectexercise3.ui.ContactViewModel
import kotlinx.coroutines.launch

class AddEditContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditContactBinding
    private val viewModel: ContactViewModel by viewModels()
    private var contactId: Long = 0L
    private var selectedAvatarName: String = AvatarCatalog.defaultName()
    private var existingContact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactId = intent.getLongExtra(EXTRA_CONTACT_ID, 0L)
        configureToolbar()
        configureAvatarPicker()
        configureSaveButton()

        if (contactId != 0L) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.contact(contactId).collect { contact ->
                        contact?.let { populateForm(it) }
                    }
                }
            }
        } else {
            updateAvatarPreview()
        }
    }

    private fun configureToolbar() {
        binding.toolbar.title = if (contactId == 0L) getString(R.string.add_contact) else getString(R.string.edit_contact)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun configureAvatarPicker() {
        binding.avatarPreview.setOnClickListener { showAvatarDialog() }
        binding.changeAvatarButton.setOnClickListener { showAvatarDialog() }
    }

    private fun configureSaveButton() {
        binding.saveButton.setOnClickListener {
            if (!validateInput()) return@setOnClickListener

            val contact = Contact(
                id = contactId,
                name = binding.nameInput.text?.toString()?.trim().orEmpty(),
                phoneNumber = binding.phoneInput.text?.toString()?.trim().orEmpty(),
                note = binding.noteInput.text?.toString()?.trim().orEmpty(),
                avatarName = selectedAvatarName
            )

            viewModel.save(contact) { finish() }
        }
    }

    private fun populateForm(contact: Contact) {
        if (existingContact == contact) return
        existingContact = contact
        selectedAvatarName = contact.avatarName
        binding.nameInput.setText(contact.name)
        binding.phoneInput.setText(contact.phoneNumber)
        binding.noteInput.setText(contact.note)
        updateAvatarPreview()
    }

    private fun validateInput(): Boolean {
        val name = binding.nameInput.text?.toString()?.trim().orEmpty()
        val phone = binding.phoneInput.text?.toString()?.trim().orEmpty()
        binding.nameLayout.error = null
        binding.phoneLayout.error = null

        var valid = true
        if (name.isBlank()) {
            binding.nameLayout.error = getString(R.string.error_name_required)
            valid = false
        }
        if (phone.isBlank()) {
            binding.phoneLayout.error = getString(R.string.error_phone_required)
            valid = false
        }
        return valid
    }

    private fun showAvatarDialog() {
        val dialogBinding = DialogAvatarPickerBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.choose_avatar)
            .setView(dialogBinding.root)
            .setNegativeButton(R.string.cancel, null)
            .create()

        dialogBinding.avatarRecyclerView.apply {
            layoutManager = GridLayoutManager(this@AddEditContactActivity, 3)
            adapter = AvatarAdapter(selectedAvatarName) { option ->
                selectedAvatarName = option.name
                updateAvatarPreview()
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun updateAvatarPreview() {
        binding.avatarPreview.setImageResource(AvatarCatalog.drawableFor(selectedAvatarName))
    }

    companion object {
        const val EXTRA_CONTACT_ID = "extra_contact_id"
    }
}
