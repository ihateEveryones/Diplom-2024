document.addEventListener('DOMContentLoaded', function () {
    var masterSelection = document.getElementById('masterSelection');
    var serviceSelection = document.getElementById('serviceSelection');
    var productSelection = document.getElementById('productSelection');
    var dateTimeSelection = document.getElementById('dateTimeSelection');
    var orderForm = document.querySelector('.orderForm');
    var continueBtnDate = document.getElementById('continueBtnDate');
    var continueBtn1 = document.getElementById('continueBtn1');
    var continueServiceBtn = document.getElementById('continueServiceBtn');
    var continueProductBtn = document.getElementById('continueProductBtn');

    var masterSelected = false;
    var serviceSelected = false;
    var productSelected = false;
    var dateTimeSelected = false;

    continueBtnDate.addEventListener('click', function () {
        dateTimeSelected = true;
        showFormIfAllSelectionsMade();
    });

    continueBtn1.addEventListener('click', function () {
        masterSelected = true;
        showFormIfAllSelectionsMade();
    });

    continueServiceBtn.addEventListener('click', function () {
        serviceSelected = true;
        showFormIfAllSelectionsMade();
    });
    continueProductBtn.addEventListener('click', function () {
        productSelected = true;
        showFormIfAllSelectionsMade();
    });

    function showFormIfAllSelectionsMade() {
        if (masterSelected && serviceSelected && dateTimeSelected && productSelected) {
            masterSelection.classList.add('hide');
            serviceSelection.classList.add('hide');
            dateTimeSelection.classList.add('hide');
            productSelection.classList.add('hide')
            orderForm.classList.remove('hide');
        }
    }
});

document.addEventListener('DOMContentLoaded', function () {
    var dateTimeList = document.getElementById('dateTimeList');
    var continueBtnDate = document.getElementById('continueBtnDate');

    var timeOptionsContainer = document.getElementById('timeOptionsContainer');
    timeOptionsContainer.addEventListener('change', function (event) {
        continueBtnDate.classList.remove('hide');
    });

    // Остановка распространения события click внутри dateTimeList
    dateTimeList.addEventListener('click', function (event) {
        event.stopPropagation();
    });

});

document.addEventListener('DOMContentLoaded', function () {
    var categories = document.querySelectorAll('.selCat');
    // Добавляем обработчик события клика на каждую категорию
    categories.forEach(function (category) {
        var categoryServices = category.querySelector('.servCat');

        // Добавляем обработчик события клика на саму категорию
        category.addEventListener('click', function (event) {
            event.preventDefault();

            // Показываем или скрываем список услуг в зависимости от его текущего состояния
            if (categoryServices.classList.contains('hide')) {
                categoryServices.classList.remove('hide');
            } else {
                categoryServices.classList.add('hide');
            }
        });
        var selectCategory = document.querySelectorAll('.selCat');
        selectCategory.forEach(function (card) {
            card.addEventListener('click', function (event) {
                // Останавливаем всплытие события, чтобы не срабатывал обработчик на родительском элементе
                event.stopPropagation();
            });
        });
        // Добавляем обработчик события клика на каждую карточку услуги внутри категории
        var serviceCards = category.querySelectorAll('.card');
        serviceCards.forEach(function (card) {
            card.addEventListener('click', function (event) {
                // Остановить распространение события
                event.stopPropagation();
                var continueServiceBtn = document.getElementById('continueServiceBtn');
                continueServiceBtn.classList.remove('hide');
            });
        });
    });
});


document.addEventListener('DOMContentLoaded', function () {

    var categories = document.querySelectorAll('.prodCat');
    var selectCategory = document.querySelectorAll('.prodCat');
    selectCategory.forEach(function (card) {
        card.addEventListener('click', function (event) {
            // Останавливаем всплытие события, чтобы не срабатывал обработчик на родительском элементе
            event.stopPropagation();
            // Пример: убираем класс hide у кнопки "Выбрать услугу"

        });
    });
    // Добавляем обработчик события клика на каждую категорию товаров
    categories.forEach(function (category) {
        // Получаем блок со списком товаров для текущей категории
        var categoryProduct = category.querySelector('.productCat');

        // Добавляем обработчик события клика на саму категорию
        category.addEventListener('click', function (event) {
            // Если клик был на чекбоксе, не выполняем никаких действий
            if (event.target.classList.contains('product-checkbox')) {
                return;
            }

            event.preventDefault();

            // Показываем или скрываем список товаров в зависимости от его текущего состояния
            if (categoryProduct.classList.contains('hide')) {
                categoryProduct.classList.remove('hide');
            } else {
                categoryProduct.classList.add('hide');
            }
        });
        // Добавляем обработчик события клика на каждую карточку товара внутри категории
        var productCards = category.querySelectorAll('.product-card');
        productCards.forEach(function (card) {
            card.addEventListener('click', function (event) {
                // Остановить распространение события
                event.stopPropagation();
                var continueProductBtn = document.getElementById('continueProductBtn');
                continueProductBtn.classList.remove('hide');
            });
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var masterCards = document.querySelectorAll('.master-card');
    masterCards.forEach(function (card) {
        card.addEventListener('click', function (event) {
            // Останавливаем всплытие события, чтобы не срабатывал обработчик на родительском элементе
            event.stopPropagation();
            // Пример: убираем класс hide у кнопки "Выбрать услугу"
            var continueBtn1 = document.getElementById('continueBtn1');
            continueBtn1.classList.remove('hide');
        });
    });
});


document.addEventListener('DOMContentLoaded', function () {
    var selectedMasterCard; // Переменная для хранения выбранной карточки мастера
    var nextSelectionServices = document.getElementById('serviceSelection');
    var continueBtn = document.getElementById('continueBtn1'); // Получаем кнопку продолжить
    var masterSelection = document.getElementById('masterSelection');
    var orderState = document.getElementById('orderState');
    var orderForm = document.querySelector('.orderForm');
    var currentSelection = document.getElementById('currentSelection');
    continueBtn.addEventListener('click', function () {
        if (selectedMasterCard) {
            // Получаем div с классом "test"
            var masterDiv = orderForm.querySelector('.master'); // Находим форму внутри div

            var clonedMasterCard = selectedMasterCard.cloneNode(true); // Клонируем выбранную карточку
            clonedMasterCard.classList.add('cloned-master-card');
            // var editButton = document.createElement('button');
            // editButton.textContent = 'Редактировать';

            // Создаем контейнер для значка редактирования
            var editIconContainer = document.createElement('div');
            editIconContainer.style.position = 'relative';
            editIconContainer.style.display = 'inline-block';

            // Создаем значок редактирования
            var editIcon = document.createElement('i');
            editIcon.className = 'bi bi-pencil'; // Добавляем классы Bootstrap для значка
            editIcon.style.cursor = 'pointer'; // Меняем курсор при наведении

            editIcon.addEventListener('click', function () {

                var masterSelection = document.getElementById('masterSelection');
                var selectedMaster = orderForm.querySelector('.master .master-card');
                orderState.value = 'editMaster';
                // Показываем выбор мастеров
                masterSelection.classList.remove('hide');

                // Скрываем форму заказа
                orderForm.classList.add('hide');

                // Удаляем выбранного мастера из формы заказа
                if (selectedMaster) {
                    selectedMaster.remove();
                }
            });
            // Добавляем значок редактирования в контейнер
            editIconContainer.appendChild(editIcon);

            // Находим элемент с классом select-master внутри клонированной карточки
            var selectMasterDiv = clonedMasterCard.querySelector('.select-master');

            // Добавляем контейнер со значком редактирования в select-master
            selectMasterDiv.appendChild(editIconContainer);

            // Добавляем клонированную карточку внутрь формы
            masterDiv.appendChild(clonedMasterCard);

        } else {
            alert('Пожалуйста, выберите мастера.');
        }
        if (orderState.value === 'editMaster') {
            orderForm.classList.remove('hide');
            masterSelection.classList.add('hide');
            //showOrderForm();

        } else {
            // showDateTimeSelection();
            masterSelection.classList.add('hide');
            nextSelectionServices.classList.remove('hide');


        }
    });

    var masterCards = document.querySelectorAll('.master-card'); // Получаем все карточки мастера

    masterCards.forEach(function (card) {
        card.addEventListener('click', function () {
            var radio = card.querySelector('input[type="radio"][name="id_master"]');
            if (radio) {
                radio.checked = true; // Отмечаем радиокнопку внутри карточки
                selectedMasterCard = card; // Сохраняем ссылку на выбранную карточку мастера
            }
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var continueServiceBtn = document.getElementById('continueServiceBtn'); // Кнопка "Продолжить" для услуг
    var continueProductBtn = document.getElementById('continueProductBtn'); // Кнопка "Продолжить" для товаров
    var nextSelectionProducts = document.getElementById('productSelection');
    var nextSelectionDateTime = document.getElementById('dateTimeSelection');
    var serviceSelection = document.getElementById('serviceSelection');
    var productSelection = document.getElementById('productSelection');
    var allDuration = document.querySelector('.allDuration');
    var selectedDate; // Переменная для хранения выбранной даты
    var selectedTime; // Переменная для хранения выбранного времени
    var orderState = document.getElementById('orderState');
    var continueBtn = document.getElementById('continueBtnDate'); // Получаем кнопку "Продолжить"
    var selectionDateTime = document.getElementById('dateTimeSelection')
    var orderForm = document.querySelector('.orderForm');
    function updateRangeTime() {
        if (selectedDate && selectedTime) {
            var durationInputs = document.querySelectorAll('.orderForm input[name="duration"]');
            var totalDurationMinutes = 0;
            durationInputs.forEach(function (input) {
                var durationParts = input.value.split(":"); // Разбиваем значение длительности по символу ":"
                var hours = parseInt(durationParts[0]); // Получаем часы
                var minutes = parseInt(durationParts[1]); // Получаем минуты
                totalDurationMinutes += hours * 60 + minutes; // Преобразуем длительность в минуты и добавляем к общей длительности
            });
            if (totalDurationMinutes >= 60) {
                var totalDurationHours = Math.floor(totalDurationMinutes / 60); // Целочисленное деление для часов
                var remainingMinutes = totalDurationMinutes % 60; // Оставшиеся минуты
                if (remainingMinutes === 0) {
                    allDuration.textContent = totalDurationHours + " ч.";
                } else {
                    allDuration.textContent = totalDurationHours + " ч. " + remainingMinutes + " мин."
                }
            } else {
                allDuration.textContent = totalDurationMinutes + " мин."
            }
            var timeComponents = selectedTime.split(":");
            var hours = parseInt(timeComponents[0]);
            var minutes = parseInt(timeComponents[1]);
            minutes += totalDurationMinutes;
            hours += Math.floor(minutes / 60);
            minutes = minutes % 60;
            var newTime = ("0" + hours).slice(-2) + ":" + ("0" + minutes).slice(-2);
            var test = selectedTime + " — " + newTime
            var rangeTime = document.querySelector('.dateTimeInfo span[class="rangeTime"]');
            if (rangeTime) {
                rangeTime.textContent = test;
            }
        }
    }
    continueBtn.addEventListener('click', function () {
        if (selectedDate && selectedTime) {
            var durationInputs = document.querySelectorAll('.orderForm input[name="duration"]');
            var totalDurationMinutes = 0;
            var selectedDateTime = new Date(selectedDate);
            var dayOfWeek = selectedDateTime.getDay();
            var dayOfMonth = selectedDateTime.getDate();
            var months = ['января', 'февраля', 'марта', 'апреля', 'мая', 'июня', 'июля', 'августа', 'сентября', 'октября', 'ноября', 'декабря'];
            var monthOfYear = months[selectedDateTime.getMonth()];
            var days = ['Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Суббота'];
            var dayName = days[dayOfWeek];
            var day = document.createElement('p');
            day.className = 'dayOfWeekMonth'
            var editIconContainer = document.createElement('div');
            editIconContainer.style.position = 'relative';
            editIconContainer.style.display = 'inline-block';
            var editIcon = document.createElement('i');
            editIcon.className = 'bi bi-pencil'; // Добавляем классы Bootstrap для значка
            editIcon.style.cursor = 'pointer'; // Меняем курсор при наведении
            day.name = 'dayOfWeek';
            day.textContent = dayName + ", " + dayOfMonth + " " + monthOfYear;
            var dateTimeInfo = document.createElement('div');
            dateTimeInfo.className = "dateTimeInfo";
            var dateInput = document.createElement('input');
            dateInput.type = 'hidden';
            dateInput.name = 'date';
            dateInput.value = selectedDate;
            var timeInput = document.createElement('input');
            timeInput.type = 'hidden';
            timeInput.name = 'time';
            timeInput.value = selectedTime;
            var editButton = document.createElement('button');
            editButton.textContent = 'Редактировать';
            editButton.type = 'button';
            durationInputs.forEach(function (input) {
                var durationParts = input.value.split(":"); // Разбиваем значение длительности по символу ":"
                var hours = parseInt(durationParts[0]); // Получаем часы
                var minutes = parseInt(durationParts[1]); // Получаем минуты
                totalDurationMinutes += hours * 60 + minutes;
            });
            if (totalDurationMinutes >= 60) {
                var totalDurationHours = Math.floor(totalDurationMinutes / 60); // Целочисленное деление для часов
                var remainingMinutes = totalDurationMinutes % 60; // Оставшиеся минуты
                if (remainingMinutes === 0) {
                    allDuration.textContent = totalDurationHours + " ч.";
                } else {
                    allDuration.textContent = totalDurationHours + " ч. " + remainingMinutes + " мин."
                }
            } else {
                allDuration.textContent = totalDurationMinutes + " мин."
            }
            var timeComponents = selectedTime.split(":");
            var hours = parseInt(timeComponents[0]);
            var minutes = parseInt(timeComponents[1]);
            minutes += totalDurationMinutes;
            hours += Math.floor(minutes / 60);
            minutes = minutes % 60;
            // Преобразуем часы и минуты обратно в строку времени
            var newTime = ("0" + hours).slice(-2) + ":" + ("0" + minutes).slice(-2);
            var test = selectedTime + " — " + newTime
            var rangeTime = document.createElement('span');
            rangeTime.className = 'rangeTime';
            rangeTime.textContent = test;
            var cardDateTime = document.createElement('div')
            cardDateTime.className = "card"
            var form = document.querySelector('.dateTime');
            var formCardDateTime = form.appendChild(cardDateTime);
            if (!formCardDateTime.hasChildNodes()) {
                formCardDateTime.appendChild(dateInput);
                formCardDateTime.appendChild(timeInput);
                formCardDateTime.appendChild(dateTimeInfo).appendChild(day);
                formCardDateTime.appendChild(dateTimeInfo).appendChild(rangeTime);
                formCardDateTime.appendChild(editIconContainer).appendChild(editIcon);

            } else {
                formCardDateTime.innerHTML = '';
                formCardDateTime.appendChild(dateInput);
                formCardDateTime.appendChild(timeInput);
                formCardDateTime.appendChild(dateTimeInfo).appendChild(day);
                formCardDateTime.appendChild(dateTimeInfo).appendChild(rangeTime);
                formCardDateTime.appendChild(editIconContainer).appendChild(editIcon);

            }
            editIcon.addEventListener('click', function () {
                var dateTimeSelection = document.getElementById('dateTimeSelection');
                var selectedDateTime = orderForm.querySelector('.dateTime ');
                orderState.value = 'editDateTime';
                dateTimeSelection.classList.remove('hide');
                orderForm.classList.add('hide');
                if (selectedDateTime) {
                    formCardDateTime.remove();
                }
            });
            if (orderState.value === 'editDateTime') {
                orderForm.classList.remove('hide');
                selectionDateTime.classList.add('hide');
            } else {
                selectionDateTime.classList.add('hide');
                orderForm.classList.remove('hide');
            }
        } else {
            alert('Пожалуйста, выберите дату и время.');
        }
    });

    var datePicker = document.getElementById('datepicker');
    datePicker.addEventListener('change', function () {
        selectedDate = this.value;
    });
    var timeOptionsContainer = document.getElementById('timeOptionsContainer');
    timeOptionsContainer.addEventListener('change', function (event) {
        if (event.target.matches('input[type="radio"][name="time"]')) {
            selectedTime = event.target.value;
        }
    });
    function updateTotalPrice() {
        var selectedServiceCards = document.querySelectorAll('.card.selectedService');
        var selectedProductCards = document.querySelectorAll('.card.selectedProduct');
        var totalPrice = 0;
        selectedServiceCards.forEach(function (serviceCard) {
            var priceElement = serviceCard.querySelector('input[name="price"]');
            var price = parseFloat(priceElement.value);
            totalPrice += price;
        });

        selectedProductCards.forEach(function (productCard) {
            var priceElement = productCard.querySelector('input[name="productPrice"]');
            var price = parseFloat(priceElement.value);
            totalPrice += price;
        });
        var totalPriceInput = document.getElementById('totalPrice');
        var totalPriceSpan = document.getElementById('spanTotalPrice');
        if (totalPriceSpan) {
            totalPriceSpan.textContent = totalPrice.toLocaleString('ru-RU');
        } else {
            totalPriceSpan = document.createElement('span');
            totalPriceSpan.id = 'spanTotalPrice';
            totalPriceSpan.name = 'totalPrice';
            totalPriceSpan.textContent = totalPrice.toLocaleString('ru-RU');
            var totalPriceDiv = orderForm.querySelector('.spanTotalPrice');
            totalPriceDiv.appendChild(totalPriceSpan);
        }
        if (totalPriceInput) {
            totalPriceInput.value = totalPrice;
        } else {
            totalPriceInput = document.createElement('input');
            totalPriceInput.name = 'totalPrice';
            totalPriceInput.id = 'totalPrice';
            totalPriceInput.value = totalPrice;
            totalPriceInput.type = 'hidden';
            var totalPriceDiv = orderForm.querySelector('.totalPrice');
            totalPriceDiv.appendChild(totalPriceInput);
        }
    }
    function handleCardSelection(checkboxClass, cardClass) {
        var checkboxes = document.querySelectorAll(checkboxClass);
        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener('change', function () {
                var card = this.closest('.card');
                if (this.checked) {
                    card.classList.add(cardClass);
                } else {
                    card.classList.remove(cardClass);
                }
                updateTotalPrice();

            });
        });
        var cards = document.querySelectorAll('.card.service-card');
        console.log(cards);
        cards.forEach(function (card) {
            if (!card.classList.contains('master-card')) {
                card.addEventListener('click', function (event) {
                    if (!event.target.classList.contains(checkboxClass.substring(1))) {
                        var checkbox = card.querySelector(checkboxClass);
                        console.log(checkbox);
                        checkbox.checked = !checkbox.checked;
                        if (checkbox.checked) {
                            card.classList.add(cardClass);
                        } else {
                            card.classList.remove(cardClass);
                        }
                        updateTotalPrice();

                    }
                });
            }
        });

    }
    function handleCardSelection1(checkboxClass, cardClass) {
        var checkboxes = document.querySelectorAll(checkboxClass);
        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener('change', function () {
                var card = this.closest('.card');
                if (this.checked) {
                    card.classList.add(cardClass);
                } else {
                    card.classList.remove(cardClass);
                }
                updateTotalPrice();
            });
        });
        var cards = document.querySelectorAll('.card.product-card');
        console.log(cards);
        cards.forEach(function (card) {
            if (!card.classList.contains('master-card')) {
                card.addEventListener('click', function (event) {
                    if (!event.target.classList.contains(checkboxClass.substring(1))) {
                        var checkbox = card.querySelector(checkboxClass);
                        console.log(checkbox);
                        checkbox.checked = !checkbox.checked;
                        if (checkbox.checked) {
                            card.classList.add(cardClass);
                        } else {
                            card.classList.remove(cardClass);
                        }
                        updateTotalPrice();
                    }
                });
            }
        });

    }
    continueServiceBtn.addEventListener('click', function () {
        var selectedServiceCards = document.querySelectorAll('.card.selectedService');
        if (selectedServiceCards.length > 0) {
            var servicesDiv = orderForm.querySelector('.services');
            selectedServiceCards.forEach(function (serviceCard, index) {
                var clonedServiceCard = serviceCard.cloneNode(true);
                clonedServiceCard.classList.add('clonedServiceCard');
                clonedServiceCard.classList.remove('selectedService');
                clonedServiceCard.classList.remove('card')
                var editIcon = document.querySelector('.editIconService');
                editIcon.addEventListener('click', function () {
                    clonedServiceCard.remove();
                    if (clonedServiceCard.horizontalLine) {
                        clonedServiceCard.horizontalLine.remove();
                    }
                    serviceSelection.classList.remove('hide');
                    orderForm.classList.add('hide');
                    orderState.value = 'editServices';
                });
                var existingCards = servicesDiv.querySelectorAll('.service-card');
                var alreadyAdded = Array.from(existingCards).some(function (existingCard) {
                    return existingCard.getAttribute('id') === clonedServiceCard.getAttribute('id');
                });
                if (!alreadyAdded) {
                    servicesDiv.appendChild(clonedServiceCard);
                    if (index < selectedServiceCards.length - 1) {
                        var horizontalLine = document.createElement('div');
                        horizontalLine.classList.add('horizontalLine');
                        servicesDiv.appendChild(horizontalLine);
                        clonedServiceCard.horizontalLine = horizontalLine;
                    }
                }
            });
            updateRangeTime();
            if (orderState.value === 'editServices') {
                orderForm.classList.remove('hide');
                serviceSelection.classList.add('hide');
            } else {
                serviceSelection.classList.add('hide');
                nextSelectionProducts.classList.remove('hide');
            }
        } else {
            alert('Пожалуйста, выберите услугу.');
        }
    });
    continueProductBtn.addEventListener('click', function () {
        var selectedProductCards = document.querySelectorAll('.card.selectedProduct');
        if (selectedProductCards.length > 0) {
            var productsDiv = orderForm.querySelector('.products');
            selectedProductCards.forEach(function (productCard, index) {
                var clonedProductCard = productCard.cloneNode(true);
                clonedProductCard.classList.add('clonedProductCard');
                clonedProductCard.classList.remove('selectedProduct');
                clonedProductCard.classList.remove('card');
                var editIcon = document.querySelector('.editIconProduct');
                editIcon.addEventListener('click', function () {
                    clonedProductCard.remove();
                    if (clonedProductCard.horizontalLine) {
                        clonedProductCard.horizontalLine.remove();
                    }
                    productSelection.classList.remove('hide');
                    orderForm.classList.add('hide');
                    orderState.value = 'editProduct';
                });
                var existingCards = productsDiv.querySelectorAll('.product-card');
                var alreadyAdded = Array.from(existingCards).some(function (existingCard) {
                    return existingCard.getAttribute('id') === clonedProductCard.getAttribute('id');
                });
                if (!alreadyAdded) {
                    productsDiv.appendChild(clonedProductCard);
                    if (index < selectedProductCards.length - 1) {
                        var horizontalLine = document.createElement('div');
                        horizontalLine.classList.add('horizontalLine');
                        productsDiv.appendChild(horizontalLine);
                        clonedProductCard.horizontalLine = horizontalLine;
                    }
                }
            });
            if (orderState.value === 'editProduct') {
                orderForm.classList.remove('hide');
                productSelection.classList.add('hide');
            } else {
                productSelection.classList.add('hide');
                nextSelectionDateTime.classList.remove('hide');
            }
        } else {
            alert('Пожалуйста, выберите товар.');
        }
    });
    handleCardSelection('.service-checkbox', 'selectedService');
    handleCardSelection1('.product-checkbox', 'selectedProduct');
});



