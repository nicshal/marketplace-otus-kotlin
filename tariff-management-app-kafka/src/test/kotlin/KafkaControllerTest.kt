package ru.nicshal.tariff.manegement.app.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.MockConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.MockProducer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.Test
import ru.nicshal.tariff.management.api.v1.apiV1RequestSerialize
import ru.nicshal.tariff.management.api.v1.apiV1ResponseDeserialize
import ru.nicshal.tariff.management.api.v1.models.*
import ru.nicshal.tariff.management.app.kafka.AppKafkaConfig
import ru.nicshal.tariff.management.app.kafka.AppKafkaConsumer
import ru.nicshal.tariff.management.app.kafka.ConsumerStrategyV1
import java.util.*
import kotlin.test.assertEquals


class KafkaControllerTest {
    @Test
    fun runKafka() {
        val consumer = MockConsumer<String, String>(OffsetResetStrategy.EARLIEST)
        val producer = MockProducer<String, String>(true, StringSerializer(), StringSerializer())
        val config = AppKafkaConfig()
        val inputTopic = config.kafkaTopicIn
        val outputTopic = config.kafkaTopicOut

        val app = AppKafkaConsumer(config, listOf(ConsumerStrategyV1()), consumer = consumer, producer = producer)
        consumer.schedulePollTask {
            consumer.rebalance(Collections.singletonList(TopicPartition(inputTopic, 0)))
            consumer.addRecord(
                ConsumerRecord(
                    inputTopic,
                    PARTITION,
                    0L,
                    "test-1",
                    apiV1RequestSerialize(
                        TariffCreateRequest(
                            requestId = "sss-ttttttt-3456",
                            tariff = TariffCreateObject(
                                tariffCode = "STD.KEEPACCNT.2",
                                tariffTypeCode = TariffTypes.STANDART,
                                serviceTypeCode = ServiceTypes.KEEP_ACCOUNT,
                                description = "Стандартный тариф за ведение счета",
                                status = TariffStatuses.PROJECT,
                                beginDate = "2022-05-01",
                                endDate = "2024-05-01"
                            ),
                            debug = TariffDebug(
                                mode = TariffRequestDebugModes.STUB,
                                stub = TariffRequestDebugStubs.SUCCESS
                            )
                        )
                    )
                )
            )
            app.stop()
        }

        val startOffsets: MutableMap<TopicPartition, Long> = mutableMapOf()
        val tp = TopicPartition(inputTopic, PARTITION)
        startOffsets[tp] = 0L
        consumer.updateBeginningOffsets(startOffsets)

        app.run()

        val message = producer.history().first()
        val result = apiV1ResponseDeserialize<TariffCreateResponse>(message.value())
        assertEquals(outputTopic, message.topic())
        assertEquals("sss-ttttttt-3456", result.requestId)
        assertEquals("STD.KEEPACCNT.1", result.tariff?.tariffCode)
    }

    companion object {
        const val PARTITION = 0
    }
}