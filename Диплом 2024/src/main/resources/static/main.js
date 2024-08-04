$(document).ready(function () {
    function bindFormHandlers() {
        $(document).on('submit', '[id^=editServicesForm_]', function (e) {
            e.preventDefault(); // Предотвращаем обычное поведение формы
            var formData = $(this).serialize(); // Сериализуем данные формы
            var url = $(this).attr('action');
            $.ajax({
                type: 'POST',
                url: url, // Адрес обработчика формы на сервере
                data: formData,
                success: function (response) {
                    toastr.options.progressBar = true;
                    toastr.success('Услуга успешно изменена!');
                    // Если запрос успешен, вы можете выполнить дополнительные действия здесь
                },
                error: function (error) {
                    toastr.error('Произошла ошибка при изменении услуги!');
                }
            });
        });

        $(document).on('submit', '.deleteServiceForm', function (e) {
            e.preventDefault(); // Предотвращаем обычное поведение формы
            var formData = $(this).serialize(); // Сериализуем данные формы
            var form = $(this);
            var url = $(this).attr('action');
            $.ajax({
                type: 'POST',
                url: url, // Адрес обработчика формы на сервере
                data: formData,
                success: function (response) {
                    toastr.options.progressBar = true;
                    toastr.success('Услуга успешно удалена!');
                    // Если запрос успешен, вы можете выполнить дополнительные действия здесь
                    form.closest('tr').remove();
                },
                error: function (error) {
                    toastr.error('Произошла ошибка при удалении услуги!');
                }
            });
        });
    }

    $('#editServices').click(function () {
        $.ajax({
            url: '/editDeleteService', // Замените на адрес вашего файла на сервере
            method: 'GET',
            success: function (response) {
                $('.mainWindow').html(response); // Вставляем содержимое в div
                bindFormHandlers(); // Привязываем обработчики событий к новым формам
            },
            error: function () {
                alert('Ошибка загрузки страницы newService');
            }
        });
    });

    bindFormHandlers(); // Привязываем обработчики событий к начальным формам
});
