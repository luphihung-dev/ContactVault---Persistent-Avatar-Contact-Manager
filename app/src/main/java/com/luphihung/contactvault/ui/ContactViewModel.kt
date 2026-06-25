package com.luphihung.contactvault.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luphihung.contactvault.data.Contact
import com.luphihung.contactvault.data.ContactDatabase
import com.luphihung.contactvault.data.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ContactRepository(
        ContactDatabase.getDatabase(application).contactDao()
    )

    val contacts: Flow<List<Contact>> = repository.contacts

    fun contact(id: Long): Flow<Contact?> = repository.contact(id)

    fun save(contact: Contact, onSaved: () -> Unit = {}) {
        viewModelScope.launch {
            repository.save(contact)
            onSaved()
        }
    }

    fun delete(contact: Contact) {
        viewModelScope.launch {
            repository.delete(contact)
        }
    }
}
