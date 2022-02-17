Feature: Ввожу код анализа в поле поиска

  Scenario Outline: Открываю страницу https://www.invitro.ru/radiology/
    ввожу код анализа в поле поиска и жму ввод
    Given Открываю страницу invitro.ru/radiology/
    When Ввожу <code> в строку поиска и жму ввод
    Examples:
      | code |
      | 1515 |