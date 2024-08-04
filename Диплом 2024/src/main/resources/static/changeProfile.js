$(document).ready(function () {
    var uploadFile= document.querySelector('.uploadFile');
    var file= document.querySelector('.file');
    $(uploadFile).click(function () {
        $(file).click();
    });
    toastr.options.progressBar = true;



    var email = document.querySelector('#newEmail');
    const userTabButtons = document.querySelectorAll('#userTab button');
    const masterTabButtons = document.querySelectorAll('#masterTab button');
    userTabButtons.forEach(button => {
        button.addEventListener('click', function () {
            const target = this.getAttribute('data-bs-target');
            const targetElement = document.querySelector(target);

            if (targetElement.classList.contains('show')) {
                targetElement.classList.remove('show');
                targetElement.classList.remove('active');
                this.classList.remove('active');
            }
        });
    });

    masterTabButtons.forEach(button => {
        button.addEventListener('click', function () {

            const target = this.getAttribute('data-bs-target');
            const targetElement = document.querySelector(target);

            if (targetElement.classList.contains('show')) {
                targetElement.classList.remove('show');
                targetElement.classList.remove('active');
                this.classList.remove('active');
            }
        });
    });





    $(document).ready(function () {
        var modal = $('#modal');

        $('#changePasswordBtn').click(function () {
            modal.show();
            $.ajax({
                url: '/changePassword/send-verification-code',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    email: email.value
                }),
                success: function (data) {
                    toastr.success('Код подтверждения отправлен на вашу почту !');
                },
                error: function (error) {
                    console.error('Ошибка при отправке кода подтверждения:', error);
                }
            });
        });

        $('.close').click(function () {
            modal.hide();
        });

        $(window).click(function (event) {
            if ($(event.target).is(modal)) {
                modal.hide();
            }
        });


        $('#passwordForm').submit(function (event) {
            event.preventDefault();
            var currentPassword = $('#currentPassword').val();
            var newPassword = $('#newPassword').val();
            var confirmNewPassword = $('#confirmPassword').val();
            var verificationCode = $('#verificationCode').val();

            if (newPassword !== confirmNewPassword) {
                toastr.error('Новый пароль и подтверждение пароля не совпадают!');
                alert('Новый пароль и подтверждение пароля не совпадают!');
                return;
            }

            $.ajax({
                url: '/changePassword/verify-and-change-password',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    currentPassword: currentPassword,
                    newPassword: newPassword,
                    confirmNewPassword: confirmNewPassword,
                    verificationCode: verificationCode
                }),
                success: function (data) {
                    if (data.success) {
                        toastr.success('Пароль успешно изменен!');

                        modal.hide();
                    } else {
                        toastr.error('Ошибка при смене пароля: ' + data.message);
                    }
                },
                error: function (error) {
                    console.error('Ошибка при смене пароля:', error);
                }
            });
        });
        $('#editProfileForm').on('submit', function(event) {
            event.preventDefault();

            var formData = {
                user_name: $('#userName').val(),
                surname: $('#surname').val(),
                patronymic: $('#patronymic').val(),
                phoneNumber: $('#phoneNumber').val(),
                newEmail: $('#newEmail').val()
            };

            $.ajax({
                type: 'POST',
                url: '/editProfile',
                data: formData,
                success: function(response) {
                    toastr.success('Данные успешно изменены!');

                },
                error: function(response) {
                    // Обработка ошибки
                    toastr.error('Ошибка при изменении данных: ' + data.message);
                }
            });
        });
    });
});