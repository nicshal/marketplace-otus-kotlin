package ru.nicshal.tariff.management.springapp.api.v1.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.nicshal.tariff.management.api.v1.models.*
import ru.nicshal.tariff.management.common.TrmngContext
import ru.nicshal.tariff.management.mappers.*
import ru.nicshal.tariff.management.springapp.controllers.v1.TariffController
import ru.nicshal.tariff.management.stubs.TrmngTariffStub

// Temporary simple test with stubs
@WebMvcTest(TariffController::class)
internal class TariffControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun createAd() = testStubTariff(
        "/v1/tariff/create",
        TariffCreateRequest(),
        TrmngContext().apply { tariffResponse = TrmngTariffStub.get() }.toTransportCreate()
    )

    @Test
    fun readAd() = testStubTariff(
        "/v1/tariff/read",
        TariffReadRequest(),
        TrmngContext().apply { tariffResponse = TrmngTariffStub.get() }.toTransportRead()
    )

    @Test
    fun updateAd() = testStubTariff(
        "/v1/tariff/update",
        TariffUpdateRequest(),
        TrmngContext().apply { tariffResponse = TrmngTariffStub.get() }.toTransportUpdate()
    )

    @Test
    fun deleteAd() = testStubTariff(
        "/v1/tariff/delete",
        TariffDeleteRequest(),
        TrmngContext().toTransportDelete()
    )

    @Test
    fun searchAd() = testStubTariff(
        "/v1/tariff/search",
        TariffSearchRequest(),
        TrmngContext().apply { tariffsResponse.add(TrmngTariffStub.get()) }.toTransportSearch()
    )

    private fun <Req : Any, Res : Any> testStubTariff(
        url: String,
        requestObj: Req,
        responseObj: Res,
    ) {
        val request = mapper.writeValueAsString(requestObj)
        val response = mapper.writeValueAsString(responseObj)

        mvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(response))

    }

}
