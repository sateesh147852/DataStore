package com.datastore.data

import Data
import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.lang.Exception

class ProtoRepository(context: Context) {

    private val dataStore: DataStore<Data.Person> =
        context.createDataStore("my_data", serializer = MySerializer())


    var readProto : Flow<Data.Person> = dataStore.data
       /* .catch { exception ->
            if (exception is Exception){
                emit(Data.Person.getDefaultInstance())
            }
            else{
                throw exception
            }
        }*/

    suspend fun getPerson() : Data.Person {
        return dataStore.data.first()
    }


    suspend fun updateValue(firstName: String) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().setFirstName(firstName).build()
        }
    }
}