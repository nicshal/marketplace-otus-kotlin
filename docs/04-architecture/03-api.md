# API

## Функции (эндпониты)

1. CRUDS (create, read, update, delete, search) для Tariff

## Описание сущности Tariff

1. TariffId - идентификатор тарифа
2. TariffCode - код тарифа
3. TariffType - тип тарифа (Standart/Individual)
4. ServiceType - тип услуги
5. Description - описание тарифа
6. Status - статус тарифа (Project/Active)
7. BeginDate - дата начала действия тарифа
8. EndDate - дата окончания действия тарифа

## Описание сущности Rule

1. RuleId - идентификатор правила
2. ConditionId - идентификатор условия
3. ActionId - идентификатор действия
4. Description - описание правила

## Описание сущности Condition

1. ConditionId - идентификатор условия
2. SqlStatement - sql-предложение, возвращающее true/false
3. Description - описание

## Описание сущности Action

1. ActionId - идентификатор действия (расчета)
2. SqlStatement - sql-предложение, возвращающее числовое значение расчитанной комиссии по тарифу
3. Description - описание
