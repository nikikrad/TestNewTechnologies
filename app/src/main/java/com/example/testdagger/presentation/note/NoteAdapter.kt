package com.example.testdagger.presentation.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testdagger.R

class NoteAdapter(
    private val textList: List<String>
) : RecyclerView.Adapter<NoteAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_note, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(textList[position])
    }

    override fun getItemCount(): Int {
        return textList.size
    }

    class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val textToTranslate: TextView = itemView.findViewById(R.id.tv_textToTranslate)
        private val translatedText: TextView = itemView.findViewById(R.id.tv_translatedText)

        fun bind(item: String){
            textToTranslate.text = item
            translatedText.text = item
        }
    }
}