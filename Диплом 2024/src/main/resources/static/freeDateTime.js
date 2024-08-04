document.addEventListener('DOMContentLoaded', function () {
    var times = {
        "schedule": [
            {"time": "09:00" },
            {"time": "09:30" },
            {"time": "10:00"},
            {"time": "10:30"},
            {"time": "11:00"},
            {"time": "11:30"},
            {"time": "12:00"},
            {"time": "12:30"},
            {"time": "13:00"},
            {"time": "13:30"},
            {"time": "14:00"},
            {"time": "14:30"},
            {"time": "15:00"},
            {"time": "15:30"},
            {"time": "16:00"},
            {"time": "16:30"},
            {"time": "17:00"},
            {"time": "17:30"},
            {"time": "18:00"},
            {"time": "18:30"},
            {"time": "19:00"},
            {"time": "19:30"},
            {"time": "20:00"}
        ]
    };
    var orderDetailsElements = document.getElementsByClassName('orderDetails');
    var orderDates = [];
    var orderStartTimes = [];
    var orderEndTimes = [];
    for (var i = 0; i < orderDetailsElements.length; i++) {
        var orderDetails = orderDetailsElements[i].innerText;
        var dateRegex = /Date: (\d{4}-\d{2}-\d{2})/;
        var dateMatch = orderDetails.match(dateRegex);
        var orderDate = dateMatch ? dateMatch[1] : '';
        orderDates.push(orderDate);
        orderStartTimes.push(orderDetails.split(', Start time: ')[1].split(', End time: ')[0]);
        orderEndTimes.push(orderDetails.split(', End time: ')[1]);
    }
    function isTimeOccupied(selectedDate, selectedTime) {
        for (var i = 0; i < orderDates.length; i++) {
            var orderDate = orderDates[i];
            var orderStartTime = orderStartTimes[i];
            var orderEndTime = orderEndTimes[i];
            if (selectedDate === orderDate && selectedTime >= orderStartTime && selectedTime <= orderEndTime) {
                return true;
            }
        }
        return false;
    }
    function showTimeOptions() {
        var selectedDate = document.getElementById("datepicker").value;
        if (selectedDate === '') {
            document.getElementById("timeOptionsContainer").style.display = 'none';
            return;
        }
        var timeOptionsHTML = "";
        times.schedule.forEach(function (timeSlot) {
            if (!isTimeOccupied(selectedDate, timeSlot.time)) {
                timeOptionsHTML += '<label class="time-option"><input type="radio" name="time" value="' + timeSlot.time + '"><span>' + timeSlot.time + '</span></label>';
            }
        });
        document.getElementById("timeOptionsContainer").innerHTML = timeOptionsHTML;
        document.getElementById("timeOptionsContainer").style.display = 'block';
    }
    document.getElementById("datepicker").addEventListener("change", showTimeOptions);
});