import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String m_from_whom;
    private String m_to_whom;
    private String m_msg_text;
    private SimpleDateFormat format_date= new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss");
    private Date m_date_dispatch;
    private String m_id_msg;

    
    public Message(){
        m_from_whom = "";
        m_to_whom = "";
        m_msg_text = "";
        m_id_msg = "";
        m_date_dispatch = new Date();
        m_date_dispatch.setYear(0);
        m_date_dispatch.setMonth(0);
        m_date_dispatch.setDate(1);
    }

    public Message(
            String from_whom,
            String to_whom,
            String message_text,
            String id_msg
    )
    {
        m_from_whom = from_whom;
        m_to_whom = to_whom;
        m_msg_text = message_text;
        m_id_msg = id_msg;
        m_date_dispatch = new Date();
    }

    public String getFromWhomMSG(){
        return m_from_whom;
    }

    public String getToWhomMSG(){
        return m_to_whom;
    }

    public String getMessageText(){
        return m_msg_text;
    }

    public String getStringDate(){
        return format_date.format(m_date_dispatch);
    }
    public String getMsgId() {
        return m_id_msg;
    }

    public String getStringTime(){
        return format_time.format(m_date_dispatch);
    }

    public Date getRAWDate(){
        return m_date_dispatch;
    }
}
