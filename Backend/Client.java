public class Client {
    private  String m_login = "";
    private String m_password = "";

    private Person m_person_data;


    public Client(
            String login,
            String password,
            Person person_data
    )
    {
        m_login = login;
        m_password = password;
        m_person_data = new Person(person_data);
    }

    public String getLogin(){
        return m_login;
    }
    public String getPassword(){
        return m_password;
    }
    public String getPersonFullName(){
        return m_person_data.getFullName();
    }
    public String getPersonBirthday(){
        return m_person_data.getDateOfBirth();
    }

}
