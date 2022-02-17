Feature: Переход по одному из пунктов верхнего меню:
  Запись к врачу, Анализы, Акции, Адреса

  Scenario Outline: Открываю страницу https://www.invitro.ru/radiology/
  и перехожу к одному из перечисленых пунктов меню
    Given Открываю страницу invitro.ru/radiology/
    When Кликаю по пункту верхнего меню <item>
    Examples:
      | item           |
      | Запись к врачу |