package ru.nicshal.tariff.management.app.kafka

import ru.nicshal.tariff.management.api.v1.apiV1RequestDeserialize
import ru.nicshal.tariff.management.api.v1.apiV1ResponseSerialize
import ru.nicshal.tariff.management.api.v1.models.IRequest
import ru.nicshal.tariff.management.api.v1.models.IResponse
import ru.nicshal.tariff.management.common.TrmngContext
import ru.nicshal.tariff.management.mappers.fromTransport
import ru.nicshal.tariff.management.mappers.toTransportTariff

class ConsumerStrategyV1 : ConsumerStrategy {
    override fun topics(config: AppKafkaConfig): InputOutputTopics {
        return InputOutputTopics(config.kafkaTopicIn, config.kafkaTopicOut)
    }

    override fun serialize(source: TrmngContext): String {
        val response: IResponse = source.toTransportTariff()
        return apiV1ResponseSerialize(response)
    }

    override fun deserialize(value: String, target: TrmngContext) {
        val request: IRequest = apiV1RequestDeserialize(value)
        target.fromTransport(request)
    }
}