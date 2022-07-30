package com.example.testdagger.presentation.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testdagger.databinding.FragmentNoteBinding
import com.example.testdagger.domain.dataclass.TextTranslator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NoteFragment: Fragment() {

    lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val database = Firebase.database
        val myRef = database.getReference("text")
        myRef.setValue(TextTranslator("buy", "купить"))
    }
}