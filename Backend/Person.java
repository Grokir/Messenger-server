import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    private String m_name;

    private String m_surname;

    private String m_patronymic;

    private Date m_birthday;

    private int YEAR_FORMAT = 1900;
    private int MONTH_FORMAT = 1;

    private SimpleDateFormat format_date= new SimpleDateFormat("dd.MM.yyyy");

    public Person(){
        m_name = "";
        m_surname = "";
        m_patronymic = "";
        m_birthday = new Date();
        m_birthday.setYear(0);
        m_birthday.setMonth(0);
        m_birthday.setDate(1);
    }

    public Person(
            String surname,
            String name,
            String patronymic,
            Date date
    )
    {
        m_name = name;
        m_surname = surname;
        m_patronymic = patronymic;
        m_birthday = date;
    }

    public Person(
            String surname,
            String name,
            String patronymic,
            int birth_day,
            int birth_month,
            int birth_year
    )
    {
        m_name = name;
        m_surname = surname;
        m_patronymic = patronymic;
        m_birthday = new Date();

        // в годах идёт прибавка к 1900-му году
        // нумерация месяцев начинается с 0
        m_birthday.setYear(birth_year - YEAR_FORMAT);
        m_birthday.setMonth(birth_month - MONTH_FORMAT);
        m_birthday.setDate(birth_day);
    }
    public Person(Person p){
        this.m_name = p.m_name;
        this.m_surname = p.m_surname;
        this.m_patronymic = p.m_patronymic;
        this.m_birthday = p.m_birthday;
    }
    public String getName(){
        return m_name;
    }
    public String getSurname(){
        return m_surname;
    }
    public String getPatronymic(){
        return m_patronymic;
    }
    public String getDateOfBirth(){
        return format_date.format(m_birthday);
    }
    public Date getRAWDate() { return m_birthday; }
    public String forPrint(){
        return m_surname + ' ' +
                m_name + ' ' +
                m_patronymic + '\n' +
                format_date.format(m_birthday);
    }
    public String getFullName(){
        return  m_surname + ' ' +
                m_name + ' ' +
                m_patronymic;
    }
}

