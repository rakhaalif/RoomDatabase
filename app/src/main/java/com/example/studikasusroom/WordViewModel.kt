package com.example.studikasusroom

/*
    ViewModel bertugas menyediakan data ke UI dan menjaga data tetap hidup meskipun konfigurasi perangkat berubah.
    - Menggunakan repository untuk mengambil data.
    - Mengelola operasi database dengan coroutine untuk mencegah blocking UI.
*/

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    /*
        Mengambil semua data dari repository.
        - LiveData membungkus Flow agar UI dapat mengamati perubahan data.
    */
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    /*
        Fungsi untuk memasukkan data baru ke repository.
        - viewModelScope digunakan untuk menjalankan coroutine pada lifecycle ViewModel.
    */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

/*
    Factory digunakan untuk membuat instance ViewModel dengan parameter khusus.
    - ViewModelProvider membutuhkan factory untuk ViewModel yang menerima parameter.
*/
class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
