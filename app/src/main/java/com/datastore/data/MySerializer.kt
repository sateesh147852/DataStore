package com.datastore.data

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class MySerializer :  Serializer<Data.Person>{

    override fun readFrom(input: InputStream): Data.Person {
        try {
            return Data.Person.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto",e)
        }
    }

    override fun writeTo(t: Data.Person, output: OutputStream) {
        t.writeTo(output)
    }
}