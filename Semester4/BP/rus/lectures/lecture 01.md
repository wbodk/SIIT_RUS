```table-of-contents
title: # Содержание 
style: nestedList # TOC style (nestedList|inlineFirstLevel)
minLevel: 0 # Include headings from the specified level
maxLevel: 0 # Include headings up to the specified level
includeLinks: true # Make headings clickable
debugInConsole: false # Print debug info in Obsidian console
```
# Реальная и информационная системы
## Реальный мир
**Реальный мир** - всё, что нас окружает и что мы можем воспринимать как реальное
## Система
**Система** - структурированный набор объектов (факторов, элементов) определенного состояния и поведения находящихся во взаимодействии для достижения заранее заданных целей

### Основные характеристики системы:
- цель действия
- ресурсы
- процессы
- структура
- окружение
### Реальная система
- система, как часть реального мира
- цель действия
- ресурсы (факторы)
- процессы
- структура
- окружение

### Абстрактная система
**Абстрактная система** - система, как часть абстрактного (воображаемого) мира специфицированная с помощью математических структур

## Информационная система
**Информационная система** (ИС) - модель реальной системы (процесса и ресурсов)

**Цель** построения ИС - предоставление информации, необходимой для функционирования и управления реальной системой

**Место** ИС в реальной системе - инфраструктурный компонент реальной системы предназначенный для поддержки управляющей системы реальной системы

**Составляющие** ИС:
- вычислительно-коммуникационная и программная инфраструктура
- база данных и база знаний
- приложения (программные пакеты) для работы с данными
- услуги, предоставляемые ИС
- проектная и пользовательская документация
- пользователи услуг ИС
- исполнители задач в реальной системе
- услуги по обеспечению эксплуатации и обслуживания ИС
- организация, процедуры, стандарты, техническая и программная поддержка, команды людей

*ИС, преимущественно, программный продукт*

### Развитие ИС
**Проектирование/моделирование**
- определение целей, структуры и поведения ИС
- построение (формализация) модели ИС

**Реализация**
- построение самой ИС
	- программирование ИС
	- тестирование ИС
	- конфигурирование ИС
	- ввод ИС в эксплуатацию

# Введение в базы данных
## Классическая организация файлов
Наиболее **старое** решение:
- ИС "на карточках", без поддержки компьютера
	- Средство для постоянного сохранения структуры данных - бумага
Более **продвинутое** решение:
- ИС организованы над системами файлов
	- Средство для постоянного сохранения структуры данных - система дисков
- ИС состоял из набора независимых приложений
	- каждое приложение имело собственные файлы
	- "хранилище данных" - набор файлов
	- данные об одной и той же сущности в разных файлах
	- со временем такая ИС приходит в противоречие сама с собой

**Основные недостатки классической организации**
- отсутствие связи между приложениями
	- необходимость ручного копирования одинаковых или похожих данных
- избыточность данных
	- необходимость многократного хранения одинаковых или похожих данных
- тесная связь между программами и данными
	- программа учитывает физическую структуру файла как в описании, так и в процедуре
- конкурентный доступ нескольких пользователей

**Последствия**
- затрудненное обслуживание ИС
- затрудненное дальнейшее развитие ИС

Проблемы, которые можно смягчить или даже разрешить в классической организации:
- отсутствие связи между приложениями
- избыточность
Проблема, которую практически невозможно смягчить или разрешить в классической организации:
- тесная связь между программами и данными

## База данных
**Основные идеи:**
- интеграция всех данных ИС в один большой "файл"
	- возникновение понятия базы данных
- нередундантное хранение данных
	- избегание избыточного многократного хранения одинаковых или схожих данных
- введение специального программного продукта для поддержки создания и использования базы данных
	- система управления базами данных (СУБД)
	- все программы используют данные из базы данных или обновляют их, используя исключительно услуги СУБД
- транзакционная обработка
	- сложная реализация над системой файлов
	- СУБД имеет встроенную поддержку
- многопользовательский конкурентный доступ
- авторизация пользователей

Все данные одной информационной системы интегрируются в один большой "файл" - базу данных. Появление реляционных баз данных
## Назначение СУБД / DBMS
**Содержание СУБД:**
- Язык для описания данных
	- Язык определения данных (DDL) - Data Definition Language
- Язык для манипулирования данными
	- Язык манипуляции данными (DML) - Data Manipulation Language
- Язык запросов
	- Язык запросов (Q) - Query Language

**Ядро СУБД**:
- обеспечение физической организации данных
- процедуры управления данными
- защита от несанкционированного доступа и уничтожения
- обеспечение многопользовательского режима работы
- обеспечение распределенной организации БД
- обеспечение задания схемы базы данных
	- над набором атрибутов ранее файлов формируется структура схемы БД
    - над схемой БД создается, используется и обновляется база данных

![](imgs/lecture%2001/1.png)
### Схема базы данных
Программа, использующая услуги СУБД:
- знает только схему БД, как логическую категорию
- над схемой БД использует логическую структуру данных (LSP) в соответствии с конкретной задачей
- не должна учитывать физическую структуру данных (FSP), которая, как правило, может быть очень сложной

## Индустрия 4.0
- Умные фабрики (Smart factory)
	- Продвинутые технологии
	- Кибер-физические системы (CPS)
	- Интернет вещей (IoT)
	- Беспроводные сети датчиков (WSN)
- Умные ресурсы
	- Роботы, совместные роботы, автоматизированные транспортные средства (AGV)
	- Датчики радиочастотной идентификации (RFID)
- Умные материалы
- Умное хранилище
- Умный продукт
- Интеллектуальные информационные системы (IS)
- Big Data

## Big Data
Серединой первого десятилетия 21 века наблюдается увеличение объема генерируемых данных:
- их генерируют люди с помощью
	- сервисов, таких как Facebook и YouTube
	- различных умных устройств
- наблюдаются ограничения традиционных техник хранения и управления данными
	- в основном реляционных баз данных
- в настоящее время объем генерируемых данных ежедневно увеличивается
	- в настоящее время генерируется более 30 000 ГБ каждую секунду
		- с тенденцией к дальнейшему резкому увеличению этого числа
			- помимо людей, данные теперь генерируются также и все большим количеством устройств IoT
	- множество источников данных и различных форматов данных

- появление NoSQL баз данных
	- с различными целями и возможностями

## Типы данных по структуре
**Структурированные данные**
- существует схема, которая определяет формат данных
- данные в реляционной базе данных
	- все строки имеют одинаковый формат

![](imgs/lecture%2001/2.png)

**Полуструктурированные данные**
- возможно определить схему, которая указывает возможные элементы, участвующие в структуре
	- они могут, но не обязаны существовать
- часто отображаются с помощью графиков и деревьев
- примеры: HTML, JSON, YAML код

![](imgs/lecture%2001/3.png)


**Неструктурированные данные**
- нет схемы, которая определяет формат данных
	- записи на социальных сетях или электронные письма
	- текстовые или мультимедийные документы
- данные неструктурированы с точки зрения системы их хранения

![](imgs/lecture%2001/4.png)