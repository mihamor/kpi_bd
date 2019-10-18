# Lab1. Ознайомлення з базовими операціями СУБД PostgreSQL, Мороз Михайло Володимирович, КП-73

### Предметна галузь
Сервіс “питання-відповіді” (користувачі, запитання, відповіді, рейтингування,теги)

Користувач може мати багато запитань, відповідей і рейтингів. 1:N
 
Запитання може мати багато відповідей. 1:N

Відповідь може мати багато рейтингувань. 1:N

Багато відповідей можут мати один тег і навпаки. N:M

## Diagram

![Diagram](https://raw.githubusercontent.com/mihamor/kpi_bd/master/db.png)

## Tables

### answers
![answers](https://raw.githubusercontent.com/mihamor/kpi_bd/master/answers.png)

### questions
![questions](https://raw.githubusercontent.com/mihamor/kpi_bd/master/questions.png)

### users
![users](https://raw.githubusercontent.com/mihamor/kpi_bd/master/users.png)

### ratings
![ratings](https://raw.githubusercontent.com/mihamor/kpi_bd/master/ratings.png)

### tags
![tags](https://raw.githubusercontent.com/mihamor/kpi_bd/master/tags.png)

### tag_question
![tag_question](https://raw.githubusercontent.com/mihamor/kpi_bd/master/tag_question.png)
