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

aid - унікальний ID, integer

creation_date - дата створення, timestamp

qid - посилання на питання, integer

uid - посилання на користувачa що залишив відповідь, integer

answert_text - текст відповіді, text

![answers](https://raw.githubusercontent.com/mihamor/kpi_bd/master/answers.png)

### questions

qid - унікальний ID, integer

creation_date - дата створення, timestamp

uid - посилання на користувачa що залишив питання, integer

answert_text - текст відповіді, text

essence - заголовок питання, основна суть, text

description - додатковий опис питання, text

![questions](https://raw.githubusercontent.com/mihamor/kpi_bd/master/questions.png)

### users

uid - унікальний ID, integer

username - ім'я користувача, text

fullname - повне ім'я, text

passhash - хеш паролю, text

![users](https://raw.githubusercontent.com/mihamor/kpi_bd/master/users.png)

### ratings

rid - унікальний ID, integer

quantity - кількісний еквівалент, integer

aid - посилання на питання, integer

uid - посилання на користувача що залишив рейтинг, integer

![ratings](https://raw.githubusercontent.com/mihamor/kpi_bd/master/ratings.png)

### tags

tagname - унікальний ідентифікатор тегу, text

description - додатковий опис тегy, integer

![tags](https://raw.githubusercontent.com/mihamor/kpi_bd/master/tags.png)

### tag_question

tagname - посилання на тег, text

qid - посилання на питання, integer

![tag_question](https://raw.githubusercontent.com/mihamor/kpi_bd/master/tag_question.png)
