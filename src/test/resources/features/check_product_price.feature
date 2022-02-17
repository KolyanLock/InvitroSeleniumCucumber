Feature: Проверка стоимости продукта

  Scenario Outline: Открываю страницу https://www.invitro.ru/radiology/
  Выбираю Анализы -> Гематологические исследования -> Клинический анализ крови
  запоминаю сумму анализа, добавляю его в корзиру и проверяю стоимость
    Given Открываю страницу invitro.ru/radiology/
    When Выбираю Анализы -> <leftScrollMEnuItem1> -> <LeftScrollMEnuItem2>
    And Запоминаю сумму <n> по списку анализа и добавляю его в корзину
    Then Захожу в корзирн и проверяю стоимость
    Examples:
      | leftScrollMEnuItem1           | LeftScrollMEnuItem2      | n |
      | Гематологические исследования | Клинический анализ крови | 1 |