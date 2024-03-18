package com.example.week4_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week4_anmp.R
import com.example.week4_anmp.databinding.FragmentStudentListBinding
import com.example.week4_anmp.model.Student
import com.example.week4_anmp.viewmodel.ListViewModel

class StudentListFragment : Fragment() {
    private lateinit var binding:FragmentStudentListBinding
    private lateinit var viewmodel:ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentListBinding.inflate(inflater, container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(this)[ListViewModel::class.java]
        viewmodel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = studentListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewmodel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
            // update the check box, change color, adding new widget, and so on
        })

        
    }
}