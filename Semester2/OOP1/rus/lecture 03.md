```table-of-contents
title: # Содержание
style: nestedList # TOC style (nestedList|inlineFirstLevel)
minLevel: 0 # Include headings from the specified level
maxLevel: 0 # Include headings up to the specified level
includeLinks: true # Make headings clickable
debugInConsole: false # Print debug info in Obsidian console
```
# Основные понятия ООП
В объектно-ориентированном программировании при описании проблемы необходимо выявить сущности (единицы наблюдения), находящиеся в мире (домене), в котором существует и решается проблема. Необходимо определить, какие сущности находятся в мире, описать их и перечислить операции над ними, с помощью которых решается проблема.

Как можно группировать данные об определённой сущности?
- Для одного человека: `String ime, prezime;`
- Для нескольких людей - массивы? `String[][] osoba = new String[2][2](); osoba[0][0] = "Pera"; osoba[0][1]="Perić"; osoba[1][0] = "Đura"; osoba[1][1]="Đurić";`
- Или через длинные списки? `double[] rotiraj(double x, double y, double ugaoRotacije)`
- Классами?

Объектно-ориентированное программирование сводится к идентификации сущностей в каком-либо домене, указанию их свойств и написанию операций над этими свойствами. В объектно-ориентированной терминологии сущности описываются **классами**, свойства являются **атрибутами**, а операции - **методами**.

Пример 1
Если мы пишем программу, которая вычисляет площадь круга, миром, в котором находится круг, является декартова прямоугольная координатная система, а описание круга состоит в указании его радиуса. Процесс решения площади круга сводится к вычислению согласно соответствующей формуле:
$$
P = r^{2} \times \pi
$$
```java
class Krug {
    /** Полупречник круга **/
    double r;
    
    /** Вычисляет площадь круга **/
    double povrsina() {
        return r * r * Math.PI;
    }
}
```

Пример 2
С другой стороны, если необходимо написать программу, которая проверяет, находится ли заданная точка внутри, на или вне круга, то у нас есть две сущности в домене: круг и заданная точка. Круг необходимо описать его координатами и радиусом, а точку - её координатами.
```java
class Tacka {
    /** x координата */
    double x;
    /** y координата */
    double y;
}

class Krug {
    /** Центр круга */
    Tacka centar;
    /** Полупречник круга */
    double r;
}

class Dekart {
    /** Вычисляет расстояние между двумя заданными точками */
    double udaljenost(Tacka a, Tacka b) {
        return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    } /** Выводит, находится ли заданная точка внутри, на или вне круга, на основе расстояния от центра круга. **/
    void proveri(Krug k, Tacka t) {
        double d = udaljenost(k.centar, t);
        if (d < k.r)
            System.out.println("внутри круга.");
        else if (d == k.r)
            System.out.println("на кругу");
        else
            System.out.println("вне круга");
    }
}

class Test {
    public static void main(String[] args) {
        Tacka t = new Tacka();
        t.x = 5;
        t.y = 5;

        Tacka centarKruga = new Tacka();
        centarKruga.x = 0;
        centarKruga.y = 0;

        Krug k = new Krug();
        k.centar = centarKruga;
        k.r = 5;

        Dekart pomoc = new Dekart();
        pomoc.proveri(k, t);
    }
}
```

![](imgs/lecture%2003/1.png)

![](imgs/lecture%2003/2.png)
Повторение:
**Класс** - модель объекта. Включает в себя:
- **Атрибуты**
- **Методы**
**Объект** - экземпляр класса

# Класс и объект
Все является объектом.  Невозможно определить функции и переменные вне класса - поэтому листинг начинается с ключевого слова `class`. Экземпляры класса называются **объектами**. Объекты создаются с использованием ключевого слова `new`.

## Ссылка на объект
```java
Automobil a;
a = new Automobil();
```
Локальная переменная `a` не является объектом, а ссылкой на объект.

![](imgs/lecture%2003/3.png)

### Значения по умолчанию для атрибутов

| Примитивный тип | Значение по умолчанию |
| --------------- | --------------------- |
| boolean         | false                 |
| char            | '\u 0000'             |
| byte            | (byte) 0              |
| short           | (short) 0             |
| int             | 0                     |
| long            | 0 L                   |
| float           | 0.0 f                 |
| double          | 0.0 d                 |

**Ссылки как атрибуты класса** - Null
**Локальные переменные** - Не имеют значения по умолчанию, использование до инициализации вызовет ошибку компиляции!

### Ссылка на объект как параметр метода
```java
void test(Automobil a) {
    a.radi = true;
}

// ...

Automobil x = new Automobil();
x.radi = false;
test(x);
```

![](imgs/lecture%2003/4.png)

![](imgs/lecture%2003/5.png)

## Оператор присваивания значения
```java
Automobil a = new Automobil();
Automobil b = new Automobil();
b = a;
```
При этом происходит копирование значения ссылки!

![](imgs/lecture%2003/7.png)

## Литерал null
Если мы хотим инициализировать ссылку так, чтобы она не указывала на объект, мы присваиваем такой переменной значение `null`, т.е. литерал `null`:
```java
Automobil a = null;
```
В этом случае переменная `a` будет ссылаться на нулевой объект.
## Глубокое копирование против поверхностного копирования
Что происходит, когда мы применяем оператор `=` к ссылкам? – тогда и исходная переменная, и новая, указывают на один и тот же объект.

![](imgs/lecture%2003/16.png)

Что если мы создадим метод `copy()`, который возвращает копию исходного объекта? Все зависит от атрибутов:
- Если атрибуты примитивные, все в порядке
- Если атрибуты не являются примитивными, тогда получаем поверхностную копию
Если оператором `=` фактически создается копия значения переменной, где проблема? Если мы создаем метод: `Automobil copy() {...}`, который будет создавать копию объекта, тогда мы должны создать копию всех атрибутов из оригинала. Оператор `=` для примитивных типов выполняет глубокое копирование. Для ссылок не выполняется глубокое копирование объекта, а копируются сами ссылки

### Поверхностная копия
```java
Automobil copy() {
    Automobil ret = new Automobil();
    ret.prednjiLevi = this.prednjiLevi;
    ret.prednjiDesni = this.prednjiDesni;
    ret.zadnjiLevi = this.zadnjiLevi;
    ret.zadnjiDesni = this.zadnjiDesni;
    return ret;
}
```

![](imgs/lecture%2003/17.png)

### Глубокая копия
```java
Automobil copy() {
    Automobil ret = new Automobil();
    ret.prednjiLevi = this.prednjiLevi.copy();
    ret.prednjiDesni = this.prednjiDesni.copy();
    ret.zadnjiLevi = this.zadnjiLevi.copy();
    ret.zadnjiDesni = this.zadnjiDesni.copy();
    return ret;
}
```

![](imgs/lecture%2003/18.png)
## Ключевое слово static
Определяет статические атрибуты и методы. Статические атрибуты и методы существуют даже без создания объекта – поэтому к ним можно обратиться по имени класса
- `StaticTest.i++;`
Статические атрибуты имеют одно и то же значение во всех объектах – если изменить статический атрибут в одном объекте, он изменится и во всех остальных объектах
Назначение статических методов:
- Доступ и работа со статическими атрибутами
- Общие методы, для которых не нужно создавать объект
```java
class StaticTest {
    static int i = 47;

    static void metoda() {
        i++;
    }
}

...

StaticTest st1 = new StaticTest();
StaticTest st2 = new StaticTest();

...

st1.i++; // то же самое, что и st2.i++;
StaticTest.i++;
StaticTest.metoda();
```
- `System.out.println();`
- `Math.random();`
- `Math.sin();`
- `Math.PI;`
- `public static void main(String[] args) {...}`
- `java Hello` $\rightarrow$ `Hello.main(args)`

### Статический блок
Статический блок выполняется только один раз, при первом использовании класса. Внутри статического блока можно обращаться только к статическим атрибутам и вызывать только статические методы
```java
class Test {
    static int a;
    static int b;

    static void f() {
        b = 6;
    }

    static {
        a = 5;
        f();
    }
}
```

# Методы

## Перегрузка методов
В классе может быть несколько методов с одним и тем же именем. Они различаются по параметрам. Методы никогда не отличаются возвращаемым значением
```java
class A {
    int metoda() { ... }
    int metoda(int i) { ... }
    int metoda(String s) { ... }
}
```

В данном примере класса `A` определены три метода с одним и тем же именем `metoda`, но с разными параметрами.

## Параметры и результат метода
Параметры могут быть:
- Примитивные типы
- Ссылки на объекты

Результат может быть:
- Примитивным типом
- Ссылкой на объект
Метод возвращает значение с помощью команды: `return значение` или `return (значение)`

# Массивы и объекты
```java
int a[]; // массив ещё не создан!
a = new int[5];
int a[] = { 1, 2, 3, 4, 5 };

Automobil[] parking = new Automobil[20];
for(int i = 0; i < parking.length; i++)
    parking[i] = new Automobil();
```

## Массивы примитивных типов
![](imgs/lecture%2003/8.png)

![](imgs/lecture%2003/9.png)

![](imgs/lecture%2003/10.png)

![](imgs/lecture%2003/11.png)

## Массивы ссылок на объекты
![](imgs/lecture%2003/13.png)

![](imgs/lecture%2003/14.png)

## Создание и заполнение
1. При объявлении переменной (инициализация при объявлении)
```java
Automobil[] parking = { new Automobil(), new Automobil(), new Automobil()};
```

2. В любом месте программы:
```java
Automobil[] parking;
// ...
parking = new Automobil[] { new Automobil(), new Automobil(), new Automobil() };
```

## Многомерные массивы
```java
int a[][] = { {1, 2, 3 }, {4, 5, 6 } };
int a[][] = new int[2][3];
int a[][] = new int[2][];

for(int i = 0; i < a.length; i++) {
    a[i] = new int[3];
}

Automobil[][] a = { { new Automobil(), new Automobil() }, { new Automobil(), new Automobil() } };
```
```java
Automobil[][] parking = new Automobil[2][];
for (int i = 0; i < parking.length; i++) {
	parking[i] = new Automobil[3];
	for (int j = 0; j < parking[i].length; j++) // Исправлено на j++
		parking[i][j] = new Automobil();
	}
}
```

![](imgs/lecture%2003/15.png)

# Инициализация объектов. Конструктор
Если мы хотим выполнить особые действия при создании объекта определенного класса, мы создаем конструктор.

Конструктор автоматически вызывается при создании объекта:
```java
Automobil a = new Automobil();
```
Если мы не создаем конструктор, компилятор автоматически создаст конструктор по умолчанию, который ничего не делает.

Вместо того чтобы каждый раз писать это:
```java
Automobil a = new Automobil();
a.prednjiLevi = new Tocak();
a.prednjiDesni = new Tocak();
a.zadnjiLevi = new Tocak();
a.zadnjiDesni = new Tocak();
```
создается конструктор в классе Automobil, в котором будет написано:
```java
Automobil() { 
    prednjiLevi = new Tocak(); 
    prednjiDesni = new Tocak(); 
    zadnjiLevi = new Tocak(); 
    zadnjiDesni = new Tocak(); 
}
```

Конструктор
```java
class A { 
    A() { 
        System.out.println("конструктор"); 
    } 
} 

... 

A переменная = new A(); 

// на консоли будет написано: 
// конструктор
```

Конструктор с параметрами
```java
class A { 
    A(String s) { 
        System.out.println(s); 
    } 
} 

... 

A переменная = new A("blabla"); 

// на консоли будет написано: 
// blabla
```

## Подразумеваемый конструктор
Если внутри определения класса не указан конструктор, компилятор автоматически сгенерирует так называемый **подразумеваемый** конструктор без параметров, с пустым телом. Этот конструктор не создается на уровне исходного кода, а на уровне байт-кода (скомпилированного кода).

Пример создания объекта с использованием подразумеваемого конструктора: 
```java
    Automobil a = new Automobil();
```

## Перегрузка конструктора
Мы можем иметь несколько конструкторов. Если мы не создаем конструктор по умолчанию, но создаем хотя бы еще один конструктор, конструктор по умолчанию больше не создается в байт-коде!

**Рекомендация**: всегда создавайте конструктор по умолчанию.
```java
class Tacka {
    /** x координата */
    double x;
    /** y координата */
    double y;
    /** Подразумеваемый конструктор 
     * x и y устанавливаются в ноль */
    Tacka() {
        x = 0;
        y = 0;
    }
}
```
```java
/** Конструктор, который принимает координаты */
Tacka(double x, double y) { this.x = x; this.y = y; }

/** Конструктор, который принимает точку */
Tacka(Tacka t) { this.x = t.x; this.y = t.y; }
```
```java
class TestTacaka {
    public static void main(String[] args) {
        Tacka t1 = new Tacka();
        Tacka t2 = new Tacka(2, 2);
        Tacka t3 = new Tacka(t2);
    }
}
```

## Ключевое слово this и конструкторы
```java
class Krug {
    Tacka centar;
    double r;

    Krug() {
        centar = new Tacka();
    }

    Krug(double x, double y, double r) {
        this(); // вызов предыдущего конструктора
        centar.x = x;
        centar.y = y;
        this.r = r;
    }
}
```

# Сборщик мусора
- Работает как отдельный процесс в фоновом режиме
- Автоматическое освобождение памяти
- Автоматическая дефрагментация памяти
- Вызывается по необходимости – мы можем явно вызвать его следующим кодом: `System.gc();`, но Сборщик мусора сам "решает", освобождать ли память – вызов этого метода всего лишь предложение Сборщику мусора начать уборку
```java
class GCTest {
    GCTest() {
        System.out.println("Конструктор");
    }

    protected void finalize() throws Throwable {
        System.out.println("finalized");
    }

    public static void main(String[] args) {
        GCTest test = new GCTest();
        System.out.println("main running...");
        System.gc();
    }
}
```
- Не существует деструктора
- Мы можем написать специальный метод `finalize()`, который вызывается непосредственно перед освобождением памяти, занимаемой объектом – мы не имеем гарантии, что он будет вызван
- Возможна ли утечка памяти?