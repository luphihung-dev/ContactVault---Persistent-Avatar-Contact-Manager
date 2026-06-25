package com.example.projectexercise3.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectexercise3.data.Contact
import com.example.projectexercise3.data.ContactDatabase
import com.example.projectexercise3.data.ContactRepository
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
