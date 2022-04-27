# OtusHightloadHW-PersonsGen
Генератор пользователей в бд для ДЗ №2, курса Otus. Highload Architect.
JDK 11.
MySQL 8.

---
### Сборка:
> mvn clean install

### Запуск:
> java -jar /target/original-persons-gen.jar

### Команды:
Генерация пользователей:
> generate <кол-во имен (м/ж)> <кол-во фамилий (м/ж)>

Пример:
> generate 100 100 \
> ... \
> Generated list '200' persons \
> Add Person(guid=ff13a18f80454f5dab9eef3e007d965d... \
> Add Person(guid=2383bcfeaefa4365831167c7462883bf... \
> ... \
> =========== All persons added ===========

---