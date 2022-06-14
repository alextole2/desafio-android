package com.picpay.desafio.android.presentation.fragments

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.bases.BaseFragment
import com.picpay.desafio.android.databinding.FragContactBinding
import com.picpay.desafio.android.extensions.showToastShortText
import com.picpay.desafio.android.extensions.viewBinding
import com.picpay.desafio.android.presentation.adapters.UserListAdapter
import com.picpay.desafio.android.presentation.viewModels.ContactViewModel

class ContactFragment : BaseFragment<ContactViewModel>() {

    override val binding by viewBinding(FragContactBinding::inflate)
    private val listAdapter: UserListAdapter by lazy { UserListAdapter() }

    override fun initComponents() {
        with(binding) {
            recyclerView.apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun initObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) { binding.userListProgressBar.isVisible = it }
            messages.observe(viewLifecycleOwner) {
                binding.recyclerView.visibility = View.GONE
                this@ContactFragment.context?.showToastShortText(getString(it))
            }
            contacts.observe(viewLifecycleOwner) {
                binding.recyclerView.visibility = View.VISIBLE
                listAdapter.submitList(it)
            }
            loadContacts()
        }
    }

}