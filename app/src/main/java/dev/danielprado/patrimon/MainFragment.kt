package dev.danielprado.patrimon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.danielprado.patrimon.databinding.FragmentMainBinding

class MainFragment: Fragment() {
    lateinit private var layoutBinding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutBinding = FragmentMainBinding.inflate(inflater)
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        layoutBinding.btnValueByDesiredPercentage.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_valueByDesiredPercentageActivity)
        }

        layoutBinding.btnWeightByValue.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_weightByValueFragment)
        }

        layoutBinding.btnDiferencialValue.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_diferencialValueFragment)
        }
    }
}