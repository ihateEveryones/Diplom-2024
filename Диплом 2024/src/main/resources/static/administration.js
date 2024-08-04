$(document).ready(function () {
    var mainWindow = document.querySelector('.mainWindow');
    function removeEventListeners() {
        // $(document).off('click', 'th.sortable');
        // $(document).off('click', '.sortable');
        // $(document).off('click', '.editServiceCategoryBtn');
        // $(document).off('click', '.deleteServiceCategory');
        $(document).off();
    }

    function loadContent(url, errorMessage, removeListeners = false) {
        if (removeListeners) {
            removeEventListeners();

        }
        $.ajax({
            url: url,
            method: 'GET',
            success: function (response) {
                mainWindow.classList.remove('hide');
                $('.mainWindow').html(response); // Вставляем содержимое в div
            },
            error: function () {
                alert(errorMessage);
            }
        });
    }

    $('#createService').click(function () {
        loadContent('/createService', 'Ошибка загрузки страницы createService', true);
    });


    $('#createProduct').click(function () {
        loadContent('/createNewProduct', 'Ошибка загрузки страницы createNewProduct', true);
    });

    $('#editServiceCategories').click(function () {
        loadContent('/editDeleteServiceCategory', 'Ошибка загрузки страницы editDeleteServiceCategory', true);
    });

    $('#editProductCategories').click(function () {
        loadContent('/editDeleteProductCategory', 'Ошибка загрузки страницы editDeleteProductCategory', true);
    });

    $('#editProducts').click(function () {
        loadContent('/editDeleteProduct', 'Ошибка загрузки страницы editDeleteProduct', true);
    });

    $('#editServices').click(function () {
        loadContent('/editDeleteService', 'Ошибка загрузки страницы editDeleteService', true);
    });

    $('#editUsers').click(function () {
        loadContent('/editDeleteUser', 'Ошибка загрузки страницы editDeleteUser', true);
    });
    $('#createReports').click(function () {
        loadContent('/createReports', 'Ошибка загрузки страницы createReports', true);
    });
});
