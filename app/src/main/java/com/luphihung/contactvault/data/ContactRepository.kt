package com.luphihung.contactvault.data

import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {
    val contacts: Flow<List<Contact>> = contactDao.observeContacts()

    fun contact(id: Long): Flow<Contact?> = contactDao.observeContact(id)

    suspend fun save(contact: Contact) {
        if (contact.id == 0L) {
            contactDao.insert(contact)
        } else {
            contactDao.update(contact)
        }
    }

    suspend fun delete(contact: Contact) = contactDao.delete(contact)
}
