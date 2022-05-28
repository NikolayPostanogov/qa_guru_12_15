 package guru.qa.tests;

import guru.qa.testBase.TestBase;
import guru.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static java.lang.String.format;

@Tag("demoqa")
public class JenkinsTest extends TestBase {
    String imgName = "img.png",
            imgPath = "img/" + imgName;

    @CsvSource(value = {
            "Alex | Egorov | test1@qa.guru | Male | 0001112223 | 2000 | May | 17 | Maths | adress1 | Sports | NCR | Delhi ",
            "Egor | Alexov | test2@qa.guru | Other | 3332221110 | 1980 | January | 01 | Maths | adress2 | Sports | NCR | Delhi"
    }, delimiter = '|')

    @ParameterizedTest(name = "{0} {1}")
    @DisplayName("Параметризованный тест формы пользователя ")
    void formTests(String firstName,
                   String lastName,
                   String email,
                   String gender,
                   String phoneNumber,
                   String year,
                   String month,
                   String day,
                   String subject,
                   String address,
                   String hobby,
                   String state,
                   String city) {

        String expectedFullName = format("%s %s", firstName, lastName);
        String expectedBirthDate = format("%s %s,%s", day, month, year);
        String expectedStateAndCity = format("%s %s", state, city);

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .setBirthDate(day, month, year)
                .setSubject(subject)
                .setAddress(address)
                .setHobby(hobby)
                .setPicture(imgPath)
                .setState(state)
                .setCity(city)
                .clickSubmit();

        registrationFormPage.checkTitleResult()
                .checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", expectedBirthDate)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", imgName)
                .checkResult("Address", address)
                .checkResult("State and City", expectedStateAndCity);
    }
}

