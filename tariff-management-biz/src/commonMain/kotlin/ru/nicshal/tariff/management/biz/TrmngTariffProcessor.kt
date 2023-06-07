package ru.nicshal.tariff.management.biz

import ru.nicshal.tariff.management.common.TrmngContext
import ru.nicshal.tariff.management.stubs.TrmngTariffStub

class TrmngTariffProcessor {
    suspend fun exec(ctx: TrmngContext) {
        ctx.tariffResponse = TrmngTariffStub.get()
    }
}