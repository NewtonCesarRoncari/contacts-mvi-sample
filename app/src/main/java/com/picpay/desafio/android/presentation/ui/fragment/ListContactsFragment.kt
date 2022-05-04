package com.picpay.desafio.android.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.ui.fragment.recyclerview.adapter.UserListAdapter
import com.picpay.desafio.android.databinding.FragmentListContactsBinding
import com.picpay.desafio.android.presentation.viewmodel.ListContactsViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListContactsFragment : Fragment() {

    private val binding by lazy { FragmentListContactsBinding.inflate(layoutInflater) }
    private val viewModel: ListContactsViewModel by viewModel()
    private val adapter = UserListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listeners()

        binding.recyclerView.adapter = adapter
        binding.userListProgressBar.visibility = VISIBLE
    }

    private fun listeners() {
        viewModel.getContacts()

        viewModel.contacts.observe(viewLifecycleOwner, { users ->
            disableProgressBar()

            adapter.users = users
        })

        viewModel.onRequisitionError.observe(viewLifecycleOwner, {
            disableProgressBar()
            disableVRecyclerView()
            showMessageError()
        })
    }

    private fun disableProgressBar() {
        binding.userListProgressBar.visibility = GONE
    }

    private fun disableVRecyclerView() {
        binding.recyclerView.visibility = GONE
    }

    private fun showMessageError() {
        Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show()
    }
}