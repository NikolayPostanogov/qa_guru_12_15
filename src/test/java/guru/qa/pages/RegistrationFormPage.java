package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    CalendarComponent calendar = new CalendarComponent();

    //locators
    SelenideElement StateAndCityInput = $("#stateCity-wrapper");
    //actions

    @Step("Открываем страницу automation-practice-form")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    @Step("Задаем имя")
    public RegistrationFormPage setFirstName(String firstName) {
        $("#firstName").setValue(firstName);

        return this;
    }

    @Step("Задаем фамилию")
    public RegistrationFormPage setLastName(String lastName) {
        $("#lastName").setValue(lastName);

        return this;
    }

    @Step("Задаем email")
    public RegistrationFormPage setEmail(String email) {
        $("#userEmail").setValue(email);

        return this;
    }

    @Step("Задаем пол")
    public RegistrationFormPage setGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();

        return this;
    }

    @Step("Задаем номер телефона")
    public RegistrationFormPage setPhoneNumber(String phoneNumber) {
        $("#userNumber").setValue(phoneNumber);

        return this;
    }

    @Step("Задаем дату рождения")
    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Задаем предмет")
    public RegistrationFormPage setSubject(String subject) {
        $("#subjectsInput").setValue(subject).pressEnter();

        return this;
    }

    @Step("Задаем адрес")
    public RegistrationFormPage setAddress(String address) {
        $("#currentAddress").setValue(address);

        return this;
    }

    @Step("Задаем хобби")
    public RegistrationFormPage setHobby(String hobby) {
        $("#hobbiesWrapper").$(byText(hobby)).click();

        return this;
    }

    @Step("Загружаем изображение")
    public RegistrationFormPage setPicture(String imgPath) {
        $("#uploadPicture").uploadFromClasspath(imgPath);

        return this;
    }

    @Step("Задаем субъект")
    public RegistrationFormPage setState(String state) {
        $("#state").click();
        StateAndCityInput.$(byText(state)).click();

        return this;
    }

    @Step("Задаем город")
    public RegistrationFormPage setCity(String city) {
        $("#city").click();
        StateAndCityInput.$(byText(city)).click();

        return this;
    }

    @Step("Нажимаем submit")
    public RegistrationFormPage  clickSubmit() {
        $("#submit").click();

        return this;
    }

    @Step("Проверяем корректность заголовка формы")
    public RegistrationFormPage checkTitleResult () {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    @Step("Проверяем корректность заполнения данных в поле {key}")
    public RegistrationFormPage checkResult(String key, String value) {
        $(".table-responsive").$(byText(key)).
                parent().shouldHave(text(value));

        return this;
    }
}
