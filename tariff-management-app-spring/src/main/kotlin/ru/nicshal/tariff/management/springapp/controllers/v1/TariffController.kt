package ru.nicshal.tariff.management.springapp.controllers.v1

import org.springframework.web.bind.annotation.*
import ru.nicshal.tariff.management.api.v1.models.*
import ru.nicshal.tariff.management.common.TrmngContext
import ru.nicshal.tariff.management.mappers.*
import ru.nicshal.tariff.management.stubs.TrmngTariffStub

@RestController
@RequestMapping("v1/tariff")
class TariffController {

    @PostMapping("create")
    fun createAd(@RequestBody request: TariffCreateRequest): TariffCreateResponse {
        val context = TrmngContext()
        context.fromTransport(request)
        context.tariffResponse = TrmngTariffStub.get()
        return context.toTransportCreate()
    }

    @PostMapping("read")
    fun readAd(@RequestBody request: TariffReadRequest): TariffReadResponse {
        val context = TrmngContext()
        context.fromTransport(request)
        context.tariffResponse = TrmngTariffStub.get()
        return context.toTransportRead()
    }

    @RequestMapping("update", method = [RequestMethod.POST])
    fun updateAd(@RequestBody request: TariffUpdateRequest): TariffUpdateResponse {
        val context = TrmngContext()
        context.fromTransport(request)
        context.tariffResponse = TrmngTariffStub.get()
        return context.toTransportUpdate()
    }

    @PostMapping("delete")
    fun deleteAd(@RequestBody request: TariffDeleteRequest): TariffDeleteResponse {
        val context = TrmngContext()
        context.fromTransport(request)
        return context.toTransportDelete()
    }

    @PostMapping("search")
    fun searchAd(@RequestBody request: TariffSearchRequest): TariffSearchResponse {
        val context = TrmngContext()
        context.fromTransport(request)
        context.tariffsResponse.add(TrmngTariffStub.get())
        return context.toTransportSearch()
    }

}