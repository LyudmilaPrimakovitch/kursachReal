# kursachReal
Программа должна обеспечивать возможность:
	Входить под логином и паролем 3 разным ролям (начальник склада, кладовщик, поставщик). Регистрация начальника происходит при самом пер-вом запуске программы, его пароль шифруется; начальник регистрирует кла-довщиков (получить доступ могут только зарегистрированные начальником кладовщики); поставщики регистрируются самостоятельно (при нажатии на кнопку «регистрация») и получают доступ к системе.
	Оформления товарно-транспортных накладных. Изначально подается заявка от поставщика (заполняется специальная форма; при некорректном за-полнении заявка незамедлительно отклоняется). После данная заявка рас-сматривается и заверяется/отклоняется кладовщиком (заполняются поля «со-ответствие качеству» и «срок годности»; при заверении информация о посту-пившем товаре вносится в БД с пометкой «принято»; при отклонении вносит-ся причина отклонения заявки).
	Система нотификации пользователей. 
	Возможность выбора нескольких складов для поставщика.
	Просмотра всех принятых и не принятых товаров. Эту информацию может просмотреть начальник склада.
	Заблокировать определенного поставщика. Это действие может произ-вести только начальник склада.
	Просмотра поставщиков с наибольшими по объему поставками, с наибольшим числом отклоненных заявок. Это действие может произвести начальник склада и кладовщик.
	Сменить используемый в настоящее время пароль (для всех ролей).
	Удалить данные логина и пароля уволенного кладовщика. Это действие может произвести только начальник склада.
Программа может также включать в себя следующий функционал (осуществляется начальником склада или программно):
	 Вывод заполненной ТТН в pdf-файл при необходимости (осуществ-ляет только кладовщик при заверении ТТН).
	 Сообщение об отклонении заявки/принятии товара на склад автома-тически отправляется на email поставщика.
	 Определение наилучшего поставщика (т.е. поставщика с наиболь-шим отношением объема поставок к количеству отклоненных заявок).
	 Определение самого популярного товара (типа товара, объемы по-ставки которого наибольшие).
	 Сортировка товаров на складе по сроку годности (у кого раньше за-канчивается, тот выше в таблице вывода на экран).
	 Просмотр общего объема поступившего товара определенного ти-па.
	 Просмотр общей загруженности склада (отношение объема товаров на складе с не истекшим сроком годности к общему объему склада в про-центном представлении).
	 Просмотр данных всех актуальных поставщиков.
