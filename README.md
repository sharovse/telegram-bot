# telegramExample
Example for Telegram

Run maven:
    mvn spring-boot:run

Run java:

    Собрать maven jar
	mvn clean package -DskipTests
    Запуск:
	Скопируй файл со свойствами в target/
	    cp src/main/resources/application.properties target/application.properties
	cd target/
	    java -jar telegram-bot-0.0.1-SNAPSHOT.jar 

	    а можно без application.properties:

	    java -Dbot.proxy.host=127.0.0.1 -Dbot.proxy.port=8080  -jar telegram-bot-0.0.1-SNAPSHOT.jar


