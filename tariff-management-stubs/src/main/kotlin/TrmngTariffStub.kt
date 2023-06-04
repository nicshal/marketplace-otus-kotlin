package ru.nicshal.tariff.management.stubs

import ru.nicshal.tariff.management.common.models.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn

object TrmngTariffStub {
    fun get() = TrmngTariff(
        id = TrmngTariffId("777"),
        tariffCode = "STD.KEEPACCNT.1",
        tariffTypeCode = TrmngTariffTypes.STANDART,
        serviceTypeCode = TrmngServiceTypes.KEEP_ACCOUNT,
        description = "Стандартный тариф за ведение счета",
        status = TrmngStatuses.PROJECT,
        beginDate = LocalDate.parse("2022-05-01").atStartOfDayIn(TimeZone.UTC),
        endDate = LocalDate.parse("2024-05-01").atStartOfDayIn(TimeZone.UTC),
        ownerId = TrmngUserId("test_user"),
        permissionsClient = mutableSetOf(
            TrmngTariffPermissionClient.READ,
            TrmngTariffPermissionClient.UPDATE,
            TrmngTariffPermissionClient.DELETE,
            TrmngTariffPermissionClient.MAKE_ACTIVE,
        )
    )

    fun prepareResult(block: TrmngTariff.() -> Unit): TrmngTariff = get().apply(block)

    fun prepareSearchList(filter: String, status: TrmngStatuses) = listOf(
        trmngTariffProject("777_001", filter, status),
        trmngTariffProject("777_002", filter, status),
        trmngTariffProject("777_003", filter, status),
        trmngTariffProject("777_004", filter, status),
        trmngTariffProject("777_005", filter, status),
        trmngTariffProject("777_006", filter, status),
    )

    private fun trmngTariffProject(id: String, filter: String, status: TrmngStatuses) =
        trmngTariff(get(), id = id, filter = filter, status = status)

    private fun trmngTariffActive(id: String, filter: String, status: TrmngStatuses) =
        trmngTariff(get(), id = id, filter = filter, status = status)

    private fun trmngTariff(base: TrmngTariff, id: String, filter: String, status: TrmngStatuses) = base.copy(
        id = TrmngTariffId(id),
        description = "desc $filter $id",
        status = status,
    )

}