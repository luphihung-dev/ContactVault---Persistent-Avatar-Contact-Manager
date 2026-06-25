package com.luphihung.contactvault

import com.luphihung.contactvault.data.Contact
import org.junit.Assert.assertEquals
import org.junit.Test

class ContactEntityTest {
    @Test
    fun newContactStartsWithDefaultId() {
        val contact = Contact(
            name = "Alex Morgan",
            phoneNumber = "0123456789",
            note = "Course representative",
            avatarName = "avatar_blue"
        )

        assertEquals(0L, contact.id)
        assertEquals("avatar_blue", contact.avatarName)
    }
}