package com.picpay.desafio.android.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.picpay.desafio.android.databinding.FragmentListContactsBinding
import com.picpay.desafio.android.presentation.ui.fragment.recyclerview.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewmodel.ListContactsViewModel
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
        binding.networkErrorAnimation.setAnimation("anim/network_error.json")
        listeners()

        binding.recyclerView.adapter = adapter
        binding.userListProgressBar.visibility = VISIBLE
    }

    private fun listeners() {
        viewModel.getContacts()

        viewModel.contacts.observe(viewLifecycleOwner, { users ->
            disableAnimation()
            disableProgressBar()

            adapter.users = users
        })

        viewModel.onRequisitionError.observe(viewLifecycleOwner, { messageError ->
            disableProgressBar()
            disableVRecyclerView()
            initNetworkAnimationError(messageError)
        })
    }

    private fun initNetworkAnimationError(messageError: String?) {
        with(binding.networkErrorAnimation) {
            scaleX = 0.5f
            scaleY = 0.5f
            visibility = VISIBLE
            playAnimation()
        }
        binding.networkErrorAnimation.visibility = VISIBLE
        binding.networkErrorMessage.visibility = VISIBLE
        binding.networkErrorMessage.text = messageError
    }

    private fun disableProgressBar() {
        binding.userListProgressBar.visibility = GONE
    }

    private fun disableAnimation() {
        binding.networkErrorMessage.visibility = INVISIBLE
        binding.networkErrorAnimation.visibility = INVISIBLE
    }

    private fun disableVRecyclerView() {
        binding.recyclerView.visibility = GONE
    }
}