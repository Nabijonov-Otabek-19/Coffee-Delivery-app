package uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.ServerRepository
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor() : ServerRepository {

    private val firestore = Firebase.firestore

    override fun loadData(categoryName: String): Flow<Result<List<CoffeeData>>> =
        callbackFlow {

            firestore.collection("categories").get()
                .addOnSuccessListener { querySnapshot ->

                    val list = arrayListOf<CoffeeData>()
                    querySnapshot.forEach { data ->
                        val category = data.get("categoryName").toString()
                        if (category == categoryName) {
                            data.reference.collection("coffeeList").get()
                                .addOnSuccessListener { subQuery ->
                                    subQuery.forEach { bookData ->
                                        list.add(bookData.toObject() as CoffeeData)
                                    }
                                    trySend(Result.success(list))
                                }
                                .addOnFailureListener { trySend(Result.failure(it)) }
                            return@addOnSuccessListener
                        }
                    }
                    trySend(Result.success(list))
                }
                .addOnFailureListener { exception ->
                    trySend(Result.failure(exception))
                }
            awaitClose()
        }
}