package com.mango.marvelworld.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.mango.marvelworld.R
import com.mango.marvelworld.databinding.ActivityDetailBinding
import com.mango.marvelworld.domain.models.CharacterDataContainer
import com.mango.marvelworld.ui.presentation.characterdetail.DetailViewModel
import com.mango.marvelworld.ui.util.RequestState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    private val characterDetailViewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterId = intent.extras?.getLong("characterId")!!
        characterDetailViewModel.fetchCharacterById(characterId = characterId)

        lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                characterDetailViewModel.requestCharacterDetailsState.collectLatest { requestState ->
                    withContext(Dispatchers.Main) {
                        if (requestState is RequestState.Loading) {
                            binding.txtCharacterName.text = getString(R.string.l_cargando)
                        } else {
                            val characterDataContainer: CharacterDataContainer =
                                (requestState as RequestState.Success<*>).data as CharacterDataContainer
                            val character = characterDataContainer.results[0]

                            // Character Image
                            binding.imgCharacterPortrait.load(
                                data = character
                                    .thumbnail.path
                                    .plus("/portrait_xlarge")
                                    .plus(".")
                                    .plus(character.thumbnail.extension)
                            )

                            // Character Name
                            binding.txtCharacterName.text = character.name

                            // Character Description
                            binding.txtCharacterDescription.text =
                                character.description.ifEmpty { "Descripción no disponible" }

                            // Comics list (Compose view)
                            binding.viewCompose.setContent {

                            }
                        }
                    }
                }
            }
        }

    }
}