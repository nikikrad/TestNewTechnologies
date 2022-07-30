package com.example.testdagger.presentation.note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testdagger.databinding.FragmentNoteBinding
import com.example.testdagger.domain.dataclass.TextTranslator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import retrofit2.http.POST

class NoteFragment: Fragment() {

    lateinit var binding: FragmentNoteBinding
    lateinit var myRef: DatabaseReference

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
        myRef = database.getReference("text")
        getDataFirebase()
//        myRef.child("hui").setValue(TextTranslator("buy", "купить"))
    }

    private fun getDataFirebase(){
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var value = snapshot.value
//                val mmm = value as TextTranslator

//                Log.e("KEK", mmm.toString())

                Log.e("KEK", value.toString())
                snapshot.children.forEach {
                    Log.e("KEK", it.value.toString())
                }
                snapshot.children.forEach {
                    it.children.forEach {
                        Log.e("KEK", it.value.toString())
                    }
                }
//                Log.e("KEK", snapshot.childrenCount.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error", error.toString())
            }

        })
    }
}