package net.jack.javawebapp;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.jack.javawebapp.database.Database;
import net.jack.javawebapp.userapplications.User;
import net.jack.javawebapp.userapplications.UserDataHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.sql.SQLException;


@Route("")
@PageTitle("Registration")
public class MainView extends VerticalLayout {

    private final Database database;
    private final User user;
    private final UserDataHandling userDH;

    private TextField forename = new TextField("Forename");
    private TextField surname = new TextField("Surname");
    private EmailField email = new EmailField("Email-Address");
    private PasswordField password = new PasswordField("Password");
    private CheckboxGroup<String> gender = new CheckboxGroup<>();
    private DatePicker dob = new DatePicker("Date Of Birth");


    public MainView() throws SQLException {
        this.database = new Database();
        this.user = new User();
        this.userDH = new UserDataHandling(database);

        add(getForm());

    }

    private Component getForm() {
        var layout = new VerticalLayout();

        var button = new Button("Enter");
        button.addClickShortcut(Key.ENTER);
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.setAlignItems(Alignment.CENTER);

        gender.setLabel("Gender");
        gender.setItems("Male", "Female", "Tapped in the head non binary");
        gender.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        layout.add(forename, surname, email, password, gender, dob, button);



        button.addClickListener(click -> {
            fieldValues();
            try {
                userDH.writeUserData(forename.getValue(), surname.getValue(), email.getValue(), password.getValue()
                        , gender.getValue().toString(), dob.getValue().toString());
                clearFields();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return layout;
    }

    public void fieldValues() {
       user.setForename(forename.getValue());
       user.setSurname(surname.getValue());
       user.setEmail(email.getValue());
       user.setGender(gender.getValue().toString());
       user.setDob(dob.getValue().toString());
    }

    public void clearFields() {
        forename.clear();
        surname.clear();
        email.clear();
        password.clear();
        dob.clear();
    }
}
