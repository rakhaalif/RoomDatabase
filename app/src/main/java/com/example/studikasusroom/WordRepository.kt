package com.example.studikasusroom

/*
    Repository adalah lapisan abstraksi antara DAO dan ViewModel.
    - Mengelola data dan menentukan apakah data berasal dari database lokal atau sumber lainnya.
    - Membantu menjaga arsitektur bersih dengan memisahkan logika bisnis dari ViewModel.
*/

import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    /*
        Mengambil semua data dari DAO dalam bentuk Flow.
        - Flow memungkinkan ViewModel mendapatkan pembaruan data secara reaktif.
    */
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    /*
        Fungsi ini digunakan untuk memasukkan data ke tabel Word.
        - suspend digunakan agar operasi berjalan secara asinkron.
    */
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}
