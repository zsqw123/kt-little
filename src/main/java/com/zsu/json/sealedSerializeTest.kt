package com.zsu.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import kotlinx.serialization.serializer
import org.intellij.lang.annotations.Language
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.starProjectedType

sealed interface MyType {
    @Serializable
    class StringType(val str: String) : MyType

    @Serializable
    class ListStringType(val list: List<String>) : MyType
}

open class MultiTypeSerializer<T : Any>(private val types: List<KType>) : KSerializer<T> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SealedSerializer2")

    override fun deserialize(decoder: Decoder): T {
        val jsonElement = (decoder as JsonDecoder).decodeJsonElement()
        return types.firstNotNullOf {
            try {
                val originSerializer = decoder.serializersModule.serializer(it) as KSerializer<T>
                decoder.json.decodeFromJsonElement(originSerializer, jsonElement)
            } catch (e: SerializationException) {
                null
            }
        }
    }

    override fun serialize(encoder: Encoder, value: T) = types.first {
        (it.classifier as KClass<*>).isInstance(value)
    }.let { serializer(it).serialize(encoder, value) }
}

inline fun <reified T : Any> sealedSerializer(): KSerializer<T> {
    val types = T::class.sealedSubclasses.map { it.starProjectedType }
    return MultiTypeSerializer(types)
}

val json = Json {
    serializersModule += SerializersModule {
        polymorphicDefaultDeserializer(MyType::class) {
            sealedSerializer()
        }
    }
}

fun main() {
    @Language("json") val jsonStr = """[
        |  { "str" : "str1" },
        |  { "list" : [ "str0", "str1" ] }
        |]""".trimMargin()

    val myTypes = json.decodeFromString<List<MyType>>(jsonStr)
    myTypes
}
