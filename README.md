
#### Gereksinimler;
Kullanıcılar aşağıdaki işlemleri gerçekleştirebilmelidir.
* İlan yayınlama sadece sisteme giriş yapan kullanıcı yapabilmeli
* Aktif ilanlarını görüntüleyebilmeli
* Pasif ilanlarını görüntüleyebilmeli
* Satın aldıkları paketleri(ürünleri) görebilmeli
* Kullanıcılar ilanları sadece ACTIVE ve PASSIVE statülerine güncelleyebilmeli
* Kullancıların aldıkları ürünler ödeme işlemi başarılı olduktan sonra
tanımlanmalı ve bu işlem asenkron yapılmalı

#### Sistem Kabulleri;
1. Ürünler, yukarıda belirtilen şekilde sistemde hali hazırda tanımlıdır. Ürün
   oluşturmak için yeni bir servis yazımına gerek yoktur. Sistem içerisinde
   tanımlanmaları yeterlidir.
2. Ürünler adet bazlı satılmaktadır.
3. Ürünler 10’ar adet olarak satın alınabilmektedir.
4. Ürünün geçerlilik 1 ay yani 30 gün ile sınırlıdır.
5. Ödeme işlemi için sisteme gerekli kayıtların yazılması yeterlidir.
6. Ödeme işlemi senkron yapılmalıdır.
7. İlanlar varsayılan olarak kaydedildiğinde IN_REVIEW statüsündedir. Asenkron
   olarak başka bir servis ACTIVE olarak değiştirmelidir.
8. Kullanıcılar ilanları sadece ACTIVE ve PASSIVE statülerine güncelleyebilir.

### Diagramlar

![](images/emlakcepte_database.png)
![](images/emlakcepte_mimari.png)

### Kullanılan Teknolojiler

<a href="https:// www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt= "java" width="40" height="40"/> </a>	&nbsp;&nbsp;<a href="https://www.postgresql.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original-wordmark.svg" alt="postgresql" width="40" height="40"/>&nbsp;&nbsp; <a href="https://www.docker.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="40" height="40"/> </a> &nbsp;&nbsp;<a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> &nbsp;&nbsp; <a href="https://www.rabbitmq.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/rabbitmq/rabbitmq-icon.svg" width="40" height="40"/> </a></a>

Java 8, Spring Boot, Restfull, Postgre, RabbitMQ, Microservice mimarisi
