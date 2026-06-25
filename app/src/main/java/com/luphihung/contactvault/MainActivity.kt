package com.luphihung.contactvault

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.luphihung.contactvault.data.Contact
import com.luphihung.contactvault.databinding.ActivityMainBinding
import com.luphihung.contactvault.ui.ContactAdapter
import com.luphihung.contactvault.ui.ContactViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactAdapter = ContactAdapter(
            onContactClick = { contact -> openEditor(contact.id) },
            onDeleteClick = { contact -> confirmDelete(contact) }
        )

        binding.contactRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = contactAdapter
            setHasFixedSize(true)
        }

        binding.addContactFab.setOnClickListener { openEditor() }
        binding.emptyAddButton.setOnClickListener { openEditor() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.contacts.collect { contacts ->
                    contactAdapter.submitList(contacts)
                    binding.emptyState.visibility = if (contacts.isEmpty()) View.VISIBLE else View.GONE
                    binding.contactRecyclerView.visibility = if (contacts.isEmpty()) View.GONE else View.VISIBLE
                }
            }
        }
    }

    private fun openEditor(contactId: Long? = null) {
        val intent = Intent(this, ContactEditorActivity::class.java)
        contactId?.let { intent.putExtra(ContactEditorActivity.EXTRA_CONTACT_ID, it) }
        startActivity(intent)
    }

    private fun confirmDelete(contact: Contact) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.delete_contact_title))
            .setMessage(getString(R.string.delete_contact_message, contact.name))
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.delete) { _, _ -> viewModel.delete(contact) }
            .show()
    }
}
