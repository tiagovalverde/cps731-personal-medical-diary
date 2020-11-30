package com.saubantiago.personalmedicaldiary;

import androidx.test.filters.SmallTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.text.ParseException;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class UtilsUnitTests {
    String validEmail, invalidEmail;
    String validPassword, invalidPassword; // min 8 characters
    String expectedDateFormat;
    long datetime;

    @Test
    public void email_is_invalid() {
        invalidEmail = "test@invalid";
        assertEquals(false, Utils.emailValid(null, invalidEmail));
    }

    @Test
    public void email_is_valid() {
        validEmail = "test@test.com";
        assertEquals(true, Utils.emailValid(null, validEmail));
    }

    @Test
    public void password_is_invalid() {
        invalidPassword = "isshort";
        assertEquals(false, Utils.passwordValid(null, invalidPassword));
    }

    @Test
    public void password_is_valid() {
        validPassword = "validPass123";
        assertEquals(true, Utils.passwordValid(null, validPassword));
    }

    @Test
    public void formatted_date_is_correct() throws ParseException {
        expectedDateFormat = "2020-11-20";
        datetime = java.sql.Date.valueOf(expectedDateFormat).getTime();
        assertEquals(expectedDateFormat, Utils.formattedDateTime(datetime, Utils.DATE_FORMAT));
    }
}
